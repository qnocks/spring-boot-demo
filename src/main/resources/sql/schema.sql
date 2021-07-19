DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS subscriptions;
DROP TABLE IF EXISTS meditation_courses;

CREATE TABLE users (
    id bigint PRIMARY KEY,
    login varchar(256) NOT NULL,
    password varchar(256) NOT NULL,
    email varchar(256) NOT NULL
);

CREATE TABLE subscriptions (
    id bigint PRIMARY KEY,
    end_date date,
    user_id bigint REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meditation_courses (
    id bigint PRIMARY KEY,
    title varchar(256) NOT NULL,
    description varchar(256),
    source_path varchar(256),
    rating double precision
)
