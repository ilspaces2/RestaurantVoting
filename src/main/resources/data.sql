INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
values ('admin@mail.com','first','last','admin'),
       ('user@mail.com','first','last','user');

INSERT INTO USER_ROLE (USER_ID, ROLE)
VALUES (1,'ROLE_ADMIN'),
       (2,'ROLE_USER');
