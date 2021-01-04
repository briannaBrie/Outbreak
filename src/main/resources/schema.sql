CREATE TABLE if not exists disease
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)  NOT NULL,
    transmission VARCHAR(250) NOT NULl,
    region VARCHAR(250) NOT NULl,
    symptoms TEXT NOT NULL,
    dateOfLastOutbreak DATE NOT NULl
);
CREATE TABLE if not exists outbreak
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    disease VARCHAR(50)  NOT NULL,
    region VARCHAR(250) NOT NULl,
    cases INT NOT NULL
);

CREATE TABLE if not exists users (
                                     username varchar(20) not null primary key,
                                     password varchar(100) not null,
                                     enabled boolean not null
);

CREATE TABLE if not exists authorities (
    username varchar(20) not null,
    authority varchar(20) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);




