INSERT INTO disease (id, name, transmission, region, symptoms, dateOfLastOutbreak)
VALUES (1, 'Corona', 'air borne','global', 'flu like symptoms and difficulties breathing', CURRENT_DATE);
INSERT INTO disease (id, name, transmission, region, symptoms, dateOfLastOutbreak)
VALUES (2, 'Ebola', 'air borne','Central and West Africa', 'bleeding from the major openings of your body',CURRENT_DATE);

INSERT INTO outbreak (id, disease , region, cases)
VALUES (1, 'Ebola', 'Congo',4);
INSERT INTO outbreak (id, disease , region, cases)
VALUES (2, 'Corona', 'Croatia',34);

INSERT INTO login_form (id, password , username)
VALUES (1, 'pass', 'admin');
INSERT INTO login_form (id, password , username)
VALUES (2, 'admin', 'user');