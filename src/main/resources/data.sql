INSERT INTO disease (id, name, transmission, region, symptoms) VALUES
(1, 'Corona', 'air borne','global', 'flu like symptoms and difficulties breathing'),
(2, 'Ebola', 'air borne','Central and West Africa', 'bleeding from the major openings of your body');

INSERT INTO outbreak (id, disease , region, cases) VALUES
(1, 'Ebola', 'Congo',4),
(2, 'Corona', 'Croatia',34);

INSERT INTO users (username, password, enabled) VALUES
('admin', '$2y$12$trwiNYNmYZ1mHyNAPGt42.1pn0H75FN7TmaUPbQfVvTpnflomzuiu', 1),
('student', '$2y$12$cO2zvMgoH9SChnUrHmb/WuS8fsSo2bte8JRbWKczqL5KFkqNyY1Fm', 1);

INSERT INTO authorities (username, authority) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('student', 'ROLE_USER');