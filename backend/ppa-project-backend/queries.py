from table_models import db, IdModel, LikertModel, DemoModel, AssignModel, PersonaModel, DecisionModel, CommentModel
from sqlalchemy.sql import text
from sqlalchemy.exc import IntegrityError


# queries for table PIDS

def pids_insert(pid):  # method POST
    """
    Inserts a tuple into table pids.
    :param pid: a user Prolific ID, (max.) 24 alphanumeric characters
    :return: a success/failure message
    """
    entry = IdModel(pid)
    try:
        db.session.add(entry)
        db.session.commit()
        return 'SUCCESS: Inserted into pids.'
    except IntegrityError:
        return f'FAILURE: Key {pid} already exists in table pids.'


def check_user_exists(pid):  # method GET
    """
    Check that a user with a given id exists in the table / database.
    :param pid: the Prolific ID, column 'id' in table 'pids'
    :return: True if user exists, False otherwise
    """
    row = db.session.query(IdModel).get([pid])
    db.session.commit()
    if row is None:
        return False
    else:
        return True


# queries for table PLIKERT

def plikert_insert(pid, lqs):  # method POST
    """
    Inserts a tuple into table plikert.
    :param pid: the user Prolific ID
    :param lqs: all (9) likert question answers, as an array
    :return: a success/failure message
    """
    entry = LikertModel(pid, lqs)
    try:
        db.session.add(entry)
        db.session.commit()
        return 'SUCCESS: Inserted into plikert.'
    except IntegrityError:
        return f'FAILURE: Key {pid} already exists in table plikert.'


# queries for table PDEMO

def pdemo_insert(pid, g, a, c, e, i, ih, ihe, io, iol):  # method POST
    """
    Inserts a tuple into table pdemo.
    :param pid: user Prolific ID
    :param g: gender (string)
    :param a: age (integer)
    :param c: country (string)
    :param e: employment (string)
    :param i: internet usage (integer)
    :param ih: whether a user heard of IoT (boolean)
    :param ihe: explanation for ih (string)
    :param io: whether a user owns IoT devices (boolean)
    :param iol: list of user IoT devices (string)
    :return: a success/failure message
    """
    entry = DemoModel(pid, g, a, c, e, i, ih, ihe, io, iol)
    try:
        db.session.add(entry)
        db.session.commit()
        return 'SUCCESS: Inserted into pdemo.'
    except IntegrityError:
        return f'FAILURE: Key {pid} already exists in table pdemo.'


# queries for table PPASSIGN

def ppassign_insert(pid, pqs, o, f):  # method POST
    """
    Inserts a tuple into table ppassign.
    :param pid: the current user Prolific ID
    :param pqs: the persona assignment question answers, a list of integer values starting from 1
    :param o: boolean stating whether calculated persona was overwritten by reassignment decision or not
    :param f: the degree of persona fit when assigned to the user
    :return: a success/failure message
    """
    entry = AssignModel(pid, pqs, o, f)
    try:
        db.session.add(entry)
        db.session.commit()
        return 'SUCCESS: Inserted into ppasign.'
    except IntegrityError:
        return f'FAILURE: Key {pid} already exists in table ppassign.'


# queries for table PPERSONA

def ppersona_insert(pid, p, pstr, a):  # method POST
    """
    Inserts a tuple into table ppersona.
    :param pid: a user Prolific ID
    :param p: a persona number (integer)
    :param pstr: persona name (string)
    :param a: user's assistant id (integer)
    :return: a success/failure message
    """
    entry = PersonaModel(pid, p, pstr, a)
    try:
        db.session.add(entry)
        db.session.commit()
        return 'SUCCESS: Inserted into ppersona.'
    except IntegrityError:
        return f'FAILURE: Key {pid} already exists in table ppersona.'


# queries for table PDECISION

def pdecision_insert(d, pid, t, p, de, da, a):  # method POST
    """
    Inserts a tuple into table pdecision.
    Used when a user makes a privacy decision (or changes it).
    :param d: the decision ID (increasing integer)
    :param pid: the user's Prolific ID
    :param t: timestamp
    :param p: the place (string)
    :param de: the device (string)
    :param da: the type of data (string)
    :param a: access boolean
    :return: a success/failure message
    """

    if a == 'true':
        a = True
    elif a == 'false':
        a = False
    else:
        raise ValueError('Parameter a must be \'true\' or \'false\'!')
    entry = DecisionModel(d, pid, t, p, de, da, a)
    try:
        db.session.add(entry)
        db.session.commit()
        return 'SUCCESS: Inserted into pdecision.'
    except IntegrityError:
        return f'FAILURE: Key {d} already exists in table pdecision. D is a strictly incrementing primary key.'


def pdecision_delete(d):
    """
    Deletes the tuple of pdecision where the primary key is d.
    Used when a user changes a made decision - this deletes the old decision instance, and also creates a new one.
    :param d: the unique decision ID
    :return: either a success or failure message
    """
    try:
        dec = db.session.query(DecisionModel).filter(DecisionModel.did == d)
        dec.delete()
        db.session.commit()
        return 'SUCCESS: Deleted from pdecision.'
    except IntegrityError:
        return f'FAILURE: No decision could be deleted for did {d} in pdecision.'


def get_user_decisions(pid):  # method GET
    """
    Retrieves the privacy decisions made by a user from the table 'pdecision', ordered by descending time.
    :param pid: the existing user's id
    :return: Rows of 'pdecision' where the user id matches pid.
    """
    rows = db.session.query(DecisionModel).filter_by(id=pid).order_by(DecisionModel.time.desc()).all()
    db.session.commit()
    return rows


def get_maximum_did():  # method GET
    """
    Retrieves the maximum did value from pdecision to get the next primary key to use.
    ATTENTION: The corresponding query function has a different name (get_next_higher_did).
    :return: The highest BigInteger value, given the maximum of did.
    """
    query_stmt = text('SELECT MAX(did) FROM pdecision;')
    res = db.session.execute(query_stmt)
    db.session.commit()
    return res


# queries for table PCOMMENT

def pcomment_insert(pid, t, l, r, c):  # method POST
    """
    Inserts a tuple into table pcomment.
    :param pid: user Prolific ID
    :param t: comment's timestamp
    :param l: indicated location (string)
    :param r: whether the user wants to receive such notifications (boolean)
    :param c: an (optional) comment (string)
    :return: a success/failure message
    """
    if r == 'true':
        r = True
    elif r == 'false':
        r = False
    else:
        raise ValueError('Parameter a must be \'true\' or \'false\'!')
    entry = CommentModel(pid, t, l, r, c)
    try:
        db.session.add(entry)
        db.session.commit()
        return 'SUCCESS: Inserted into pcomment.'
    except IntegrityError:
        return f'FAILURE: Compound key {pid} + {t}' \
               f' already exists in table pcomment.'


# more queries


def get_all_from_table(model):  # method GET
    """
    Retrieves all rows of the given table.
    Corresponds to queries <table_name>_get_all
    :param model: the corresponding model object (see table_models.py) of a table.
    :return: all tuples of the table
    """
    rows = db.session.query(model).all()
    db.session.commit()
    return rows
