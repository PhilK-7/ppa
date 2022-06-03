from queries import get_all_from_table


def download_table(model, path):
    """
    Downloads a table of the database and outputs it as .csv file.
    :param model: a table model of the database schema
    :param path: the path of the output file (including .csv ending)
    :return: no return itself; but writes file to specified path
    """
    rows = get_all_from_table(model)

    # determine header row
    first_row = str(rows[0])
    first_row = first_row.strip('{}')
    columns = first_row.split(",")
    column_names = []
    # get keys = column names
    for c in columns:
        kv = c.split(":")
        k = kv[0].split("\"")[1]
        column_names.append(k)

    # get rest of rows
    with open(path, 'w') as file:
        first_flag = True  # skip first row because header processed
        for row in rows:
            if first_flag:
                first_flag = False
                # set header row
                for i in range(len(column_names)):
                    write_str = column_names[i]
                    if i == len(column_names) - 1:
                        write_str += '\n'
                    else:
                        write_str += ','
                    file.write(write_str)
                continue

            row_str = str(row)  # string representation (see table_models)
            row_str = row_str.strip('{}')
            values = row_str.split(',')
            val_amt = len(values)

            for i in range(val_amt):  # write every value of the row
                if 'time' not in column_names[i]:
                    value = values[i].split(':')[1]
                else:  # special treatment when attribute is timestamp
                    value_parts = values[i].split(':')
                    value = ':'.join(value_parts[1:])
                value_parts = value.split('\"')
                if len(value_parts) == 3:
                    final_val = value_parts[1]
                else:
                    final_val = value_parts[0]

                write_str = final_val
                if i < val_amt - 1:
                    write_str += ','
                else:
                    write_str += '\n'
                file.write(write_str)
