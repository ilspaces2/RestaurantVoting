INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@gmail.com', '{noop}password'),
       ('Admin', 'admin@javaops.ru', '{noop}admin');

INSERT INTO USER_ROLE (USER_ID, ROLE)
VALUES (2,'ADMIN'),
       (2,'USER'),
       (1,'USER');
