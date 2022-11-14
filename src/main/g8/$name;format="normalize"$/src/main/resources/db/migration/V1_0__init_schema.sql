CREATE TABLE IF NOT EXISTS person
(
    name      varchar(255),
    birthDate TIMESTAMP NOT NULL,
    PRIMARY KEY (name)
);

INSERT IGNORE INTO person(name, birthDate)
VALUES ('Martin Odersky', '1970-05-19 01:42:01');
INSERT IGNORE INTO person(name, birthDate)
VALUES ('James Gosling', '1970-09-05 01:42:01');