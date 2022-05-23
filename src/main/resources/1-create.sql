drop table if exists users_table;
drop table if exists events;
create table users_table
(
    id SERIAL primary key,
    firstname VARCHAR (50),
    lastname VARCHAR (50),
    login VARCHAR (50) ,
    passwd VARCHAR (50),
    email VARCHAR (50)
);

create table event_table
(
    id SERIAL primary key,
    owner_id INT not null REFERENCES users_table(id),
    eventName VARCHAR (50),
    description VARCHAR (400),
    eventStart TIMESTAMP ,
    eventEnd TIMESTAMP ,
    location VARCHAR (50),
    likes INT
);

-- add unique pair
create table subscribed
(
    user_id SERIAL REFERENCES users_table(id),
    event_id SERIAL REFERENCES event_table(id)
);

