-- drop table if exists users_table;
-- drop table if exists event_table;
-- drop table if exists subscribed;

create table users_table
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
    owner_id INT not null REFERENCES users_table(id),
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
    user_id LONG REFERENCES users_table(id),
    event_id LONG REFERENCES event_table(id)
);
