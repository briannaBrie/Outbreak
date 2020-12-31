CREATE TABLE disease
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)  NOT NULL,
    transmission VARCHAR(250) NOT NULl,
    region VARCHAR(250) NOT NULl,
    symptoms TEXT NOT NULL,
    dateOfLastOutbreak DATE NOT NULl
);
CREATE TABLE outbreak
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    disease       VARCHAR(50)  NOT NULL,
    region VARCHAR(250) NOT NULl,
    cases INT NOT NULL
);
CREATE TABLE login_form
(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    password VARCHAR(50),
    username VARCHAR(50) NOT NULL
);