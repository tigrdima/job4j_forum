create table users (
  id serial primary key,
  username varchar(2000),
  eMail varchar unique,
  password varchar
);

create table comments (
  id serial primary key,
  username varchar(2000),
  eMail varchar unique,
  password varchar
  user_id references users(id);
);

create table posts (
  id serial primary key,
  name varchar(2000),
  description text,
  created timestamp without time zone not null default now(),
  comment_id int references comments(id),
  user_id int references users(id)
);