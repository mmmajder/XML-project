insert into Role (name)
VALUES ('ROLE_GRADJANIN');/*1*/
insert into Role (name)
VALUES ('ROLE_SLUZBENIK');/*2*/
insert into Role (name)
VALUES ('ROLE_USER');

insert into user_auth(deleted, is_enabled, last_password_set, verification_code)
values (false, true);

insert into gradjanin(email, password, name, surname, phone_number, user_auth_id, role)
values ('g@gmail.com', '$2a$10$tnplXdStY6t7kOqqKssMYedAGjJ0T3OJH2BxeT81c1YrDqOUvHLD6', 'Pera', 'Peric', '069123456', 1, 1);

insert into sluzbenik(email, password, name, surname, phone_number, user_auth_id, role)
values ('g@gmail.com', '$2a$10$tnplXdStY6t7kOqqKssMYedAGjJ0T3OJH2BxeT81c1YrDqOUvHLD6', 'Pera', 'Peric', '069123456', 1, 1);
