import json
from sqlalchemy.engine import result as r


valsep = ', '


def format_timestamp(_tuple):
    """
    Using a tuple of strings, forms the appropriate timestamp string.
    NOTE: Returns timestamps without seconds, as SQL literal queries in SQLAlchemy somehow return datetime objects
        without seconds. The seconds are still included when using pdecision_get_all.
    :param _tuple: tuple first half from loop iterations in assemble_response_json
    :return: a correct timestamp string
    """
    # get to start of timestamp position
    for i in range(len(_tuple)):
        if 'datetime' in _tuple[i]:
            _tuple = _tuple[i:]
            break
    dt_start = _tuple[0].split('(')
    tsstr = ''
    ts = dt_start[1]  # year
    ts = ts + '-' + _tuple[1].strip() + '-' + _tuple[2].strip() + _tuple[3] + ':' + _tuple[4].strip()
    ts += '\"'
    tsstr += ts
    tsstr += valsep

    # postprocess to pad with leading zeroes
    parts = tsstr.split()  # date + time
    date = parts[0].split('-')
    time = parts[1].split(':')
    final_str = date[0]
    rest_parts = date[1:] + time
    for i in range(0, 4):
        if i < 2:
            final_str += '-'
        if i == 2:
            final_str += ' '
        elif i >= 3:
            final_str += ':'
        part = rest_parts[i].strip('\",')
        while len(part) < 2:
            part = '0' + part
        final_str += part

    return '\"timestamp\": "' + final_str + '"' + valsep


def assemble_response_json(query_response, query_function):
    """
    Composes a valid JSON object to return by the backend, which includes DB query results and other information.
    Performs some manipulations on raw SQL / string / number etc. data to return the correct result JSON.
    :param query_response: the string returned by the 'str' method on a query result, based on __repr__
    :param query_function: which query was given in the HTTP(S) request (dbconnect.py)
    :return: correct JSON object to return that contains all expected information
    """
    # build head
    js = '{'  # store final, valid JSON string here
    js += '\"query function\": '
    js += '\"'
    js += query_function
    js += '\"'
    js += valsep
    js += '\"result\": '

    # append query results (rows or other type)
    if isinstance(query_response, bool):  # boolean response
        if query_response:
            js += 'true'
        else:
            js += 'false'
    elif isinstance(query_response, r.ResultProxy):  # single result returned by raw SQL query (get_next_higher_did)
        val = query_response.first()[0]  # number
        js += str(val+1)
    elif query_response.startswith('{') or query_response.startswith('['):  # SQL tuples
        js += query_response
    else:  # another non-json-form response (see POST request queries)
        js += '\"'
        js += query_response
        js += '\"'
    js += '}'

    result = json.loads(js)

    return result
