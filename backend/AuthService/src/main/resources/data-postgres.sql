insert into Role (name)
VALUES ('ROLE_GRADJANIN');/*1*/
insert into Role (name)
VALUES ('ROLE_SLUZBENIK');/*2*/
insert into Role (name)
VALUES ('ROLE_USER');

insert into user_auth(deleted, is_enabled)
values (false, true);

-- insert into gradjanin(email, password, name, surname, phone_number, user_auth_id, role)
-- values ('g@gmail.com', '$2a$10$tnplXdStY6t7kOqqKssMYedAGjJ0T3OJH2BxeT81c1YrDqOUvHLD6', 'Pera', 'Peric', '069123456', 1, 1);
--
-- insert into sluzbenik(email, password, name, surname, phone_number, user_auth_id, role)
-- values ('s@gmail.com', '$2a$10$tnplXdStY6t7kOqqKssMYedAGjJ0T3OJH2BxeT81c1YrDqOUvHLD6', 'Pera', 'Peric', '069123456', 1, 1);

insert into users(email, password, name, surname, phone_number, user_auth_id, role)
values ('g@gmail.com', '$2a$10$tnplXdStY6t7kOqqKssMYedAGjJ0T3OJH2BxeT81c1YrDqOUvHLD6', 'Gradjanin', 'Peric', '069123456', 1, 1),
       ('s@gmail.com', '$2a$10$tnplXdStY6t7kOqqKssMYedAGjJ0T3OJH2BxeT81c1YrDqOUvHLD6', 'Sluzbenik', 'Peric', '069123456', 1, 2);

