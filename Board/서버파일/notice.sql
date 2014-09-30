create database notice;
use notice;

create table board  (
  number integer(10) primary key,
  title varchar(255) not null,
  content varchar(2000) not null
);

grant select, insert, update, delete
on notice.*
to user@localhost identified by 'password';
