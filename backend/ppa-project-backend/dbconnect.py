import zipfile
from datetime import datetime
from flask import Flask, request, send_file
from flask_cors import CORS
from flask_restful import Resource, Api
import queries
from response_assembler import assemble_response_json
from table_models import db, IdModel, LikertModel, DemoModel, AssignModel, PersonaModel, DecisionModel, CommentModel
import downloader

# CONFIGURE app <here> with your custom credentials
app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = "postgresql://postgres:password@localhost:5432/postgres"
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db.init_app(app)
api = Api(app)

CORS(app)  # set access-control-allow-origin header


# define request methods here

class Redirect(Resource):
    def get(self):
        return 'Use sub-path with function name to do an API call!'


class Redirect2(Resource):
    def get(self):
        return 'Use sub-path with table name to download a table as CSV!'


def provide_status_code(query_output, method):
    """
    Checks whether a called query function did return normally, and provides the correct (error) code.
    :param method: the HTTP method of the request
    :param query_output: the output of a query function in module 'queries'
    :return: a HTTP status code
    """
    if method == 'GET':
        return 200  # can only return something, even when empty, here
    elif method == 'DELETE':
        if query_output.startswith('SUCCESS'):
            return 200  # successful delete
        else:
            return 501  # unsuccessful / not possible
    else:
        if query_output.startswith('SUCCESS'):
            return 201  # POST/PUT successful
        elif query_output.startswith('FAILURE'):
            return 500  # generic error when row not inserted


class QueryHandler(Resource):
    def get(self, query_function):
        if query_function == 'check_user_exists':
            p1 = request.args.get('pid')
            res = queries.check_user_exists(p1)

        elif query_function == 'get_user_decisions':
            p1 = request.args.get('pid')
            res = str(queries.get_user_decisions(p1))

        elif query_function == 'get_next_higher_did':
            res = queries.get_maximum_did()

        elif query_function == 'pids_get_all':
            res = str(queries.get_all_from_table(IdModel))
        elif query_function == 'plikert_get_all':
            res = str(queries.get_all_from_table(LikertModel))
        elif query_function == 'pdemo_get_all':
            res = str(queries.get_all_from_table(DemoModel))
        elif query_function == 'ppassign_get_all':
            res = str(queries.get_all_from_table(AssignModel))
        elif query_function == 'ppersona_get_all':
            res = str(queries.get_all_from_table(PersonaModel))
        elif query_function == 'pdecision_get_all':
            res = str(queries.get_all_from_table(DecisionModel))
        elif query_function == 'pcomment_get_all':
            res = str(queries.get_all_from_table(CommentModel))
        else:
            return 'Use other HTTP methods than GET for other query functions!'
        code = provide_status_code(res, 'GET')
        res = assemble_response_json(res, query_function)
        return res, code

    def post(self, query_function):
        if query_function == 'pids_insert':
            p1 = request.args.get('pid')
            res = queries.pids_insert(p1)
        elif query_function == 'plikert_insert':
            p1 = request.args.get('pid')
            p2 = request.args.get('lqs').split(',')  # comma-separated array values
            res = queries.plikert_insert(p1, p2)
        elif query_function == 'pdemo_insert':
            ps = []
            for param in ['pid', 'g', 'a', 'c', 'e', 'i', 'ih', 'ihe', 'io', 'iol']:  # parse all parameters
                p = request.args.get(param)
                # handle boolean (neither string nor number)
                if p == 'false':
                    p = False
                elif p == 'true':
                    p = True
                ps.append(p)
            res = queries.pdemo_insert(ps[0], ps[1], ps[2], ps[3], ps[4], ps[5], ps[6], ps[7], ps[8], ps[9])
        elif query_function == 'ppassign_insert':
            p1 = request.args.get('pid')
            p2 = request.args.get('pqs').split(',')  # comma-separated array values
            p3 = request.args.get('o')
            p4 = request.args.get('f')
            if p3 == 'true':
                p3 = True
            elif p3 == 'false':
                p3 = False
            else:
                raise ValueError('Parameter o must be a boolean!')
            res = queries.ppassign_insert(p1, p2, p3, p4)
        elif query_function == 'ppersona_insert':
            p1 = request.args.get('pid')
            p2 = request.args.get('p')
            p3 = request.args.get('pstr')
            p4 = request.args.get('a')
            res = queries.ppersona_insert(p1, p2, p3, p4)
        elif query_function == 'pdecision_insert':
            p1 = request.args.get('d')
            p2 = request.args.get('pid')
            p3 = request.args.get('t')
            p4 = request.args.get('p')
            p5 = request.args.get('de')
            p6 = request.args.get('da')
            p7 = request.args.get('a')
            res = queries.pdecision_insert(p1, p2, p3, p4, p5, p6, p7)
        elif query_function == 'pcomment_insert':
            p1 = request.args.get('pid')
            p2 = request.args.get('t')
            p3 = request.args.get('l')
            p4 = request.args.get('r')
            p5 = request.args.get('c')
            res = queries.pcomment_insert(p1, p2, p3, p4, p5)
        else:
            return 'Use other HTTP methods than POST for other query functions!'

        code = provide_status_code(res, 'POST')
        res = assemble_response_json(res, query_function)
        return res, code

    def delete(self, query_function):
        if query_function == 'pdecision_delete':
            p = request.args.get('d')
            if p is None:
                res = 'FAILURE: Necessary parameter not given.'
            else:
                res = queries.pdecision_delete(p)
        else:
            return 'Use other HTTP methods than DELETE for other query functions!'

        code = provide_status_code(res, 'DELETE')
        res = assemble_response_json(res, query_function)
        return res, code

    def put(self):
        return 'Use other HTTP methods than PUT for query functions!'


class Downloader(Resource):
    def get(self, table_name):
        ts = str(datetime.now())
        ts = ts.replace(' ', '_')
        ts = ts.replace(':', '-')
        dl_path = 'out/' + table_name + '_' + ts
        final_dl_path = dl_path + '.csv'  # not final when downloading all

        if table_name == 'all':  # batch download of all tables
            all_models = (IdModel, LikertModel, DemoModel, AssignModel, PersonaModel, DecisionModel, CommentModel)
            file_paths = []
            for model in all_models:
                name = model.__tablename__
                final_dl_path = 'out/' + name + '_' + ts + '.csv'  # final path for one file each
                file_paths.append(final_dl_path)
                downloader.download_table(model, final_dl_path)

            # zip files and deliver zip download
            zip_path = dl_path + '.zip'
            zf = zipfile.ZipFile(zip_path, 'w')
            for fp in file_paths:  # put all generated files in zip
                zf.write(fp)
            zf.close()
            return send_file(zip_path, as_attachment=True)

        else:  # single table download
            if table_name == 'pids':
                downloader.download_table(IdModel, final_dl_path)
            elif table_name == 'plikert':
                downloader.download_table(LikertModel, final_dl_path)
            elif table_name == 'pdemo':
                downloader.download_table(DemoModel, final_dl_path)
            elif table_name == 'ppassign':
                downloader.download_table(AssignModel, final_dl_path)
            elif table_name == 'ppersona':
                downloader.download_table(PersonaModel, final_dl_path)
            elif table_name == 'pdecision':
                downloader.download_table(DecisionModel, final_dl_path)
            elif table_name == 'pcomment':
                downloader.download_table(CommentModel, final_dl_path)
            return send_file(final_dl_path, as_attachment=True)


api.add_resource(Redirect, '/dbqueries')
api.add_resource(Redirect2, '/download')
api.add_resource(QueryHandler, '/dbqueries/<query_function>')
api.add_resource(Downloader, '/download/<table_name>')


@app.route('/')
def do():
    return 'Goto /dbqueries or /download'


if __name__ == '__main__':
    # remove ssl_context to use HTTP instead of HTTPS
    # runs on built-in Werkzeug development server; not recommended for production build!
    app.run(host='0.0.0.0', ssl_context=('certificate/ppa.crt', 'certificate/ppa.key'), debug=False)
