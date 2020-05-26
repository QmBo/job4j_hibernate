-- noinspection SqlResolve

alter table if exists car drop CONSTRAINT car_engine_id_key;
alter table body_gen add column doors_id int references doors(id);
alter table body_gen add column engine_id int references engine(id);
alter table engine add column drive_id int references drive_ref(id);
alter table engine add column gear_box_id int references gear_box_ref(id);
create table if not exists photos_ref
(
    id serial not null primary key ,
    ad_id int not null references ad(id),
    photo_id int not null references photos(id)
);
alter table ad add column add_owner_id int references users(id);
alter table users add column login varchar(200);
alter table users add column password varchar(200);
INSERT INTO users (name, expired, login, password) VALUES ('Root', '2020-05-18 10:52:49.301000', 'root', 'root');