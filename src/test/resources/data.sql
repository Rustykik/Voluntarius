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