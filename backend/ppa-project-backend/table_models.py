import json

from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()


# also refer to database_schemes.ods


class IdModel(db.Model):
    __tablename__ = 'pids'

    id = db.Column(db.String, primary_key=True)

    def __init__(self, pid):
        self.id = pid

    def __repr__(self):
        rdict = {'id': self.id}
        return json.dumps(rdict)


class LikertModel(db.Model):
    __tablename__ = 'plikert'

    id = db.Column(db.String, primary_key=True)
    lq1 = db.Column(db.SmallInteger)
    lq2 = db.Column(db.SmallInteger)
    lq3 = db.Column(db.SmallInteger)
    lq4 = db.Column(db.SmallInteger)
    lq5 = db.Column(db.SmallInteger)
    lq6 = db.Column(db.SmallInteger)
    lq7 = db.Column(db.SmallInteger)
    lq8 = db.Column(db.SmallInteger)
    lq9 = db.Column(db.SmallInteger)

    def __init__(self, pid, lqs):
        self.id = pid
        if len(lqs) != 9:
            raise ValueError('Likert question vector must have 9 answers!')
        self.lq1 = lqs[0]
        self.lq2 = lqs[1]
        self.lq3 = lqs[2]
        self.lq4 = lqs[3]
        self.lq5 = lqs[4]
        self.lq6 = lqs[5]
        self.lq7 = lqs[6]
        self.lq8 = lqs[7]
        self.lq9 = lqs[8]

    def __repr__(self):
        rdict = {'id': self.id, 'lq1': self.lq1, 'lq2': self.lq2, 'lq3': self.lq3, 'lq4': self.lq4, 'lq5': self.lq5,
                 'lq6': self.lq6, 'lq7': self.lq7, 'lq8': self.lq8, 'lq9': self.lq9}
        return json.dumps(rdict)


class DemoModel(db.Model):
    __tablename__ = 'pdemo'

    id = db.Column(db.String, primary_key=True)
    gender = db.Column(db.String)
    age = db.Column(db.SmallInteger)
    country = db.Column(db.String)
    employment = db.Column(db.String)
    inetusg = db.Column(db.SmallInteger)
    ioth = db.Column(db.Boolean)
    iothe = db.Column(db.String)
    ioto = db.Column(db.Boolean)
    iotolist = db.Column(db.String)

    def __init__(self, pid, g, a, c, e, i, ih, ihe, io, iol):
        self.id = pid
        self.gender = g
        self.age = a
        self.country = c
        self.employment = e
        self.inetusg = i
        self.ioth = ih
        self.iothe = ihe
        self.ioto = io
        self.iotolist = iol

    def __repr__(self):
        rdict = {'id': self.id, 'gender': self.gender, 'age': self.age, 'country': self.country,
                 'employment': self.employment, 'inetusg': self.inetusg, 'ioth': self.ioth,
                 'iothe': self.iothe, 'ioto': self.ioto, 'iotolist': self.iotolist}
        return json.dumps(rdict)


class AssignModel(db.Model):
    __tablename__ = 'ppassign'

    id = db.Column(db.String, primary_key=True)
    pq1 = db.Column(db.SmallInteger)
    pq2 = db.Column(db.SmallInteger)
    pq3 = db.Column(db.SmallInteger)
    pq4 = db.Column(db.SmallInteger)
    pq5 = db.Column(db.SmallInteger)
    pq6 = db.Column(db.SmallInteger)
    pq7 = db.Column(db.SmallInteger)
    pq8 = db.Column(db.SmallInteger)
    pq9 = db.Column(db.SmallInteger)
    overwritten = db.Column(db.Boolean)
    pfit = db.Column(db.SmallInteger)

    def __init__(self, pid, pqs, o, f):
        self.id = pid
        if len(pqs) != 9:
            raise ValueError('Persona assignment questions vector is of length 9!')
        self.pq1 = pqs[0]
        self.pq2 = pqs[1]
        self.pq3 = pqs[2]
        self.pq4 = pqs[3]
        self.pq5 = pqs[4]
        self.pq6 = pqs[5]
        self.pq7 = pqs[6]
        self.pq8 = pqs[7]
        self.pq9 = pqs[8]
        self.overwritten = o
        self.pfit = f

    def __repr__(self):
        rdict = {'id': self.id, 'pq1': self.pq1, 'pq2': self.pq2, 'pq3': self.pq3, 'pq4': self.pq4, 'pq5': self.pq5,
                 'pq6': self.pq6, 'pq7': self.pq7, 'pq8': self.pq8, 'pq9': self.pq9, 'overwritten': self.overwritten,
                 'pfit': self.pfit}
        return json.dumps(rdict)


class PersonaModel(db.Model):
    __tablename__ = 'ppersona'

    id = db.Column(db.String, primary_key=True)
    persona = db.Column(db.SmallInteger)
    personastr = db.Column(db.String)
    assistant = db.Column(db.SmallInteger)

    def __init__(self, pid, p, pstr, a):
        self.id = pid
        self.persona = p
        self.personastr = pstr
        self.assistant = a

    def __repr__(self):
        rdict = {'id': self.id, 'persona': self.persona, 'personastr': self.personastr, 'assistant': self.assistant}
        return json.dumps(rdict)


class DecisionModel(db.Model):
    __tablename__ = 'pdecision'

    did = db.Column(db.BigInteger, primary_key=True)
    id = db.Column(db.String)
    time = db.Column(db.DateTime)
    place = db.Column(db.String)
    device = db.Column(db.String)
    data = db.Column(db.String)
    access = db.Column(db.Boolean)

    def __init__(self, d, pid, t, p, de, da, a):
        self.did = d
        self.id = pid
        self.time = t
        self.place = p
        self.device = de
        self.data = da
        self.access = a

    def __repr__(self):
        if self.access:
            a = 'true'
        else:
            a = 'false'
        rdict = {'did': self.did, 'id': self.id, 'time': str(self.time), 'place': self.place,
                 'device': str(self.device),
                 'data': self.data, 'access': a}
        return json.dumps(rdict, indent=4)


class CommentModel(db.Model):
    __tablename__ = 'pcomment'

    id = db.Column(db.String, primary_key=True)
    time = db.Column(db.DateTime, primary_key=True)
    llocation = db.Column(db.String)
    receive = db.Column(db.Boolean)
    comment = db.Column(db.String)

    def __init__(self, pid, t, l, r, c):
        self.id = pid
        self.time = t
        self.llocation = l
        self.receive = r
        self.comment = c

    def __repr__(self):
        if self.receive:
            r = 'true'
        else:
            r = 'false'
        rdict = {'id': self.id, 'time': str(self.time), 'llocation': self.llocation,
                 'receive': r, 'comment': self.comment}
        return json.dumps(rdict)
