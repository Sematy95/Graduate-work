-- liquibase formatted sql

-- changeset dmitri:1
CREATE TYPE ROLE AS ENUM ('USER', 'ADMIN');

CREATE TABLE user_data
(   id INTEGER PRIMARY KEY NOT NULL,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    phone TEXT NOT NULL,
    email TEXT NOT NULL,
    role ROLE NOT NULL
);

CREATE TABLE ad
(   pk INTEGER PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    image TEXT NOT NULL,
    price INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    CONSTRAINT user_data_id foreign key (author_id) references user_data(id)
);

CREATE TABLE comment
(   pk INTEGER PRIMARY KEY NOT NULL,
    text TEXT NOT NULL,
    created_at BIGINT NOT NULL,
    author_id INTEGER NOT NULL,
    CONSTRAINT user_data_id foreign key (author_id) references user_data(id)
);

-- changeset dmitri:2
ALTER TABLE comment
    ADD ad_id INTEGER NOT NULL;

ALTER TABLE comment
    ADD CONSTRAINT ad_id foreign key (ad_id) references ad(pk);

-- changeset dmitri:3
ALTER TABLE user_data
    ADD image TEXT NOT NULL;

-- changeset sematy:4
ALTER TABLE user_data
DROP COLUMN IF EXISTS username ;

-- changeset sematy:5
ALTER TABLE user_data
ALTER COLUMN role SET DATA TYPE VARCHAR;

-- changeset sematy:6
create sequence IF NOT EXISTS user_data_seq start with 1 increment by 1;
create sequence IF NOT EXISTS comment_seq start with 1 increment by 1;
create sequence IF NOT EXISTS ad_seq start with 1 increment by 1;

-- changeset sematy:7
ALTER TABLE user_data
DROP COLUMN image,
ADD COLUMN image TEXT;

