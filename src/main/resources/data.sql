INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@gmail.com', '{noop}password'),
       ('Admin', 'admin@gmail.ru', '{noop}admin');

INSERT INTO USER_ROLE (USER_ID, ROLE)
VALUES (1,'ADMIN'),
       (2,'USER');
