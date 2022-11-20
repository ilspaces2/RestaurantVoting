INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
values ('admin@mail.com','first','last','{noop}admin'),
       ('user@mail.com','first','last','{noop}user');

INSERT INTO USER_ROLE (USER_ID, ROLE)
VALUES (1,'ADMIN'),
       (2,'USER');
