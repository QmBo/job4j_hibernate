create table if not exists users(
  id serial primary key,
  name varchar(2000),
  expired timestamp
);