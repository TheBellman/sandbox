create schema if not exists test;

create table if not exists test.messages (
    id varchar(36) primary key not null,
    message varchar(1024) null default (null)
);