drop table if exists users;
drop table if exists events;
create table users
(
    id INT not null primary key AUTO_INCREMENT,
    firstname VARCHAR (50),
    lastname VARCHAR (50),
    login VARCHAR (50) unique,
    passwd VARCHAR (50),
    email VARCHAR (50)
);

create table event_table
(
    id INT not null primary key AUTO_INCREMENT,
    owner_id INT not null REFERENCES users(id),
    eventName VARCHAR (50),
    description VARCHAR (400),
    eventStart DATETIME,
    eventEnd DATETIME,
    location VARCHAR (50),
    likes INT
);

-- add unique pair
create table subscribed
(
    user_id LONG REFERENCES users(id),
    event_id LONG REFERENCES event_table(id)
);

insert into users (firstname, lastname, login, passwd,email)
values ('Jhon', 'Kim', 'Jkim', '123', 'jk@mail.ru'),
       ('King', 'Kong', 'KK', 'qwer', 'KingKong@mail.ru'),
       ('Anton', 'Gandon', 'AG', 'Ant', 'Contex@mail.ru'),
       ('John', 'Cena', 'AndHisNameIs...', 'jkl;', 'JohnCena@mail.ru'),
       ('Marshal', 'MM', 'Eminem', 'MyNameIs', 'Eminem@mail.ru'),
       ('Godzilla', 'UAAARHHH', 'LittleReptile', 'WWWWW', 'Godzilla@mail.ru');

insert into event_table (owner_id, eventName, description, eventStart, eventEnd, location, likes)
values (6, 'Tokio fight', 'Large fight in tokyo with different creatures', '1999-11-01 10:11:11', '1999-11-01 15:11:11', 'Tokyo, Japan', 0),
        (5, 'Concert: my name is Hanzamay', 'Ma name is Han ZAMAY all greatest tracks of han zamay', '2022-01-01 10:11:11', '2024-11-01 15:11:11', 'Nyagan, Russia', 0),
       (5, 'Concert: my name is Hanzamay', 'Ma name is Han ZAMAY all greatest tracks of han zamay', '2022-01-01 10:11:11', '2024-11-01 15:11:11', 'Ryazan, Russia', 0),
        (5, 'Concert: my name is Hanzamay', 'Ma name is Han ZAMAY all greatest tracks of han zamay', '2022-01-01 10:11:11', '2024-11-01 15:11:11', 'Tiva, Russia', 0);

insert into subscribed (user_id, event_id)
values (6,1),
       (5, 1),
       (5, 2),
       (4,1);