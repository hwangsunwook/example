create table nuser (
  tel char(32) primary key,
  id varchar(255) not null,
  pwd varchar(255) not null
);

create table euser  (
  tel char(32) primary key,
  id varchar(255) not null,
  pwd varchar(255) not null,
  cname varchar(255) not null,
  ename varchar(255) not null,
  etel char(32) not null,
  enumber char(32) not null,
  eaddress varchar(255) not null
);