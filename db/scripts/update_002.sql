create table if not exists items(
  id serial primary key,
  description varchar(2000),
  done boolean default false,
  create_time timestamp
);