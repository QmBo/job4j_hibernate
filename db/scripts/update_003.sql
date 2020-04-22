create table if not exists engine(
    id serial primary key,
    name varchar(200)
);
create table if not exists car(
    id serial primary key,
    engine_id int not null unique references engine(id),
    name varchar(200)
);
create table if not exists driver(
    id serial primary key,
    name varchar(200)
);
create table if not exists history_owner(
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);