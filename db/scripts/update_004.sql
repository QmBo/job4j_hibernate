create table if not exists maker
(
    id serial not null primary key,
    name varchar(200) not null unique
);

create table if not exists model
(
    id serial not null primary key,
    maker_id int not null references maker,
    name varchar(200) not null
);

create table if not exists generation
(
    id serial not null primary key,
    name varchar(200) not null,
    start_year int not null,
    end_year int not null,
    model_id int not null references model,
    production boolean default false not null
);

create table if not exists body
(
    id serial not null primary key,
    name varchar(200) not null unique
);

create table if not exists body_gen
(
    id serial not null primary key,
    body_id int not null references body,
    gen_id int not null references generation
);

create table if not exists doors
(
    id serial not null primary key,
    numbers varchar(20) not null,
    body_gen_id int not null references body_gen
);

alter table engine add column body_gen_id int references body_gen;

create table if not exists gear_box
(
    id serial not null primary key,
    name varchar(200) not null
);

create table if not exists gear_box_ref
(
    id serial not null primary key,
    gear_box_id int not null references gear_box,
    engine_id int not null references engine
);

create table if not exists drive
(
    id serial not null primary key,
    name varchar(200) not null
);

create table if not exists drive_ref
(
    id serial not null primary key,
    drive_id int not null references drive,
    engine_id int not null references engine
);

alter table car add column if not exists maker_id int references maker(id);
alter table car add column if not exists model_id int references model(id);
alter table car add column if not exists generation_id int references generation(id);
alter table car add column if not exists gear_box_id int references gear_box(id);
alter table car add column if not exists drive_id int references drive(id);
alter table car add column if not exists doors_id int references doors(id);
alter table car add column if not exists body_id int references body_gen(id);

create table if not exists ad
(
    id serial primary key,
    name varchar(200),
    car_id int references car(id),
    odd bigint,
    description varchar(2000),
    price bigint,
    placed boolean default true,
    create_time timestamp,
    year int
);

create table photos
(
    id serial primary key,
    name varchar(200)
);

INSERT INTO public.body (id, name) VALUES (1, 'Седан');
INSERT INTO public.body (id, name) VALUES (2, 'Универсал');
INSERT INTO public.body (id, name) VALUES (3, 'Хэтчбэк');
INSERT INTO public.body (id, name) VALUES (4, 'Купе');
INSERT INTO public.body (id, name) VALUES (5, 'Микроавтобус');
INSERT INTO public.body (id, name) VALUES (6, 'Минивэн');
INSERT INTO public.body (id, name) VALUES (7, 'Кабриолет');
INSERT INTO public.body (id, name) VALUES (8, 'Пикап');
INSERT INTO public.body (id, name) VALUES (9, 'Фургон');
INSERT INTO public.maker (id, name) VALUES (1, 'Volkswagen');
INSERT INTO public.maker (id, name) VALUES (2, 'Toyota');
INSERT INTO public.maker (id, name) VALUES (3, 'BMW');
INSERT INTO public.model (id, maker_id, name) VALUES (1, 1, 'Golf');
INSERT INTO public.model (id, maker_id, name) VALUES (2, 1, 'Passat');
INSERT INTO public.model (id, maker_id, name) VALUES (3, 1, 'Passat CC');
INSERT INTO public.model (id, maker_id, name) VALUES (5, 2, 'Hilux');
INSERT INTO public.model (id, maker_id, name) VALUES (6, 2, 'Corolla');
INSERT INTO public.model (id, maker_id, name) VALUES (7, 3, '3');
INSERT INTO public.model (id, maker_id, name) VALUES (8, 3, '1');
INSERT INTO public.model (id, maker_id, name) VALUES (10, 3, '5');
INSERT INTO public.drive (id, name) VALUES (1, 'передний');
INSERT INTO public.drive (id, name) VALUES (2, 'задний');
INSERT INTO public.drive (id, name) VALUES (3, 'полный');
INSERT INTO public.gear_box (id, name) VALUES (1, 'Автомат');
INSERT INTO public.gear_box (id, name) VALUES (2, 'Механика');
INSERT INTO public.gear_box (id, name) VALUES (3, 'Вариатор');
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (3, 'Mk6', 2008, 2013, 1, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (4, 'B6', 2005, 2010, 2, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (5, 'B7', 2010, 2015, 2, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (6, 'B8', 2015, 2020, 2, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (2, 'Mk8', 2019, 2020, 1, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (7, 'B6', 2008, 2016, 3, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (1, 'Mk7', 2012, 2020, 1, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (8, 'Восьмое поколение', 2015, 2020, 5, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (9, 'Седьмое поколение', 2004, 2015, 5, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (10, 'Шестое поколение', 1997, 2005, 5, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (11, 'Двенадцатое поколение', 2018, 2020, 6, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (12, 'Одинадцатое поколение', 2012, 2019, 6, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (13, 'Десятое поколение', 2006, 2013, 6, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (14, 'G20', 2018, 2020, 7, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (15, 'F30', 2012, 2019, 7, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (16, 'E90', 2005, 2013, 7, false);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (17, 'F20/F21', 2011, 2020, 8, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (18, 'F40', 2019, 2020, 8, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (19, 'G30/G31', 2016, 2020, 10, true);
INSERT INTO public.generation (id, name, start_year, end_year, model_id, production) VALUES (20, 'F10/F11/F07', 2013, 2017, 10, false);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (1, 1, 19);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (2, 2, 19);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (3, 2, 20);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (4, 1, 20);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (5, 1, 17);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (6, 3, 17);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (7, 3, 18);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (8, 1, 14);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (9, 2, 14);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (10, 1, 15);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (11, 2, 15);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (12, 1, 16);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (13, 2, 16);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (14, 4, 16);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (15, 7, 16);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (16, 1, 11);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (17, 2, 11);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (18, 3, 11);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (19, 1, 12);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (20, 2, 12);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (21, 1, 13);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (22, 2, 13);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (23, 8, 8);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (24, 8, 9);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (25, 8, 10);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (26, 1, 7);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (27, 1, 4);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (28, 2, 4);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (29, 1, 5);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (30, 2, 5);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (31, 1, 6);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (32, 2, 3);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (33, 3, 3);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (34, 7, 3);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (36, 2, 1);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (37, 3, 1);
INSERT INTO public.body_gen (id, body_id, gen_id) VALUES (38, 3, 2);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (10, 4, 27);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (38, 4, 8);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (43, 4, 1);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (20, 2, 25);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (34, 4, 12);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (22, 5, 18);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (19, 4, 25);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (6, 5, 33);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (4, 5, 36);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (25, 5, 20);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (23, 5, 17);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (29, 4, 5);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (44, 5, 38);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (11, 5, 28);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (5, 3, 33);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (37, 4, 10);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (15, 4, 31);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (42, 5, 2);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (35, 5, 13);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (17, 2, 23);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (16, 4, 23);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (30, 5, 6);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (12, 4, 26);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (13, 4, 29);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (28, 5, 7);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (26, 4, 21);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (3, 5, 37);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (2, 3, 37);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (9, 5, 32);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (36, 5, 11);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (18, 4, 24);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (31, 3, 6);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (21, 4, 16);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (33, 2, 14);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (40, 5, 3);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (32, 2, 15);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (41, 4, 4);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (24, 4, 19);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (39, 5, 9);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (1, 2, 34);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (27, 5, 22);
INSERT INTO public.doors (id, numbers, body_gen_id) VALUES (14, 5, 30);

INSERT INTO public.engine (id, name, body_gen_id) VALUES (2, '1.5d 116 л.c. дизель', 7);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (15, '2.5 218 л.c. бензин', 15);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (24, '1.5 136 л.c. бензин', 10);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (25, '2.0d 190 л.c. дизель', 10);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (19, '2.0d 177 л.c. дизель', 15);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (106, '2.0d 190 л.c. дизель', 1);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (48, '1.3 95 л.c. бензин', 19);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (49, '1.5h 74 л.c. гибрид', 19);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (50, '1.5 103 л.c. бензин', 19);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (51, '1.5 109 л.c. бензин', 19);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (92, '1.4 160 л.c. бензин', 34);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (93, '1.6d 105 л.c. дизель', 34);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (94, '1.2 105 л.c. бензин', 34);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (13, '2.5 218 л.c. бензин', 13);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (99, '1.2 105 л.c. бензин', 32);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (100, '1.2 86 л.c. бензин', 32);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (101, '1.4 122 л.c. бензин', 32);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (102, '1.4 160 л.c. бензин', 32);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (103, '2.0d 140 л.c. дизель', 32);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (79, '1.4 122 л.c. бензин', 28);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (80, '1.8 152 л.c. бензин', 28);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (23, '2.0 156 л.c. бензин', 15);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (36, '1.2 116 л.c. бензин', 18);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (37, '1.8h 98 л.c. гибрид', 18);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (38, '2.0h 153 л.c. гибрид', 18);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (39, '2.0 168 л.c. бензин', 18);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (63, '3.0d 171 л.c. дизель', 24);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (17, '2.0d 177 л.c. дизель', 13);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (30, '2.0d 150 л.c. дизель', 9);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (31, '2.0d 190 л.c. дизель', 9);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (81, '1.5 130 л.c. бензин', 38);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (82, '1.5 150 л.c. бензин', 38);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (83, '2.0d 115 л.c. дизель', 38);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (84, '2.0d 150 л.c. дизель', 38);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (95, '1.2 105 л.c. бензин', 33);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (8, '1.5d 116 л.c. дизель', 6);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (35, '2.0 156 л.c. бензин', 8);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (77, '1.4 122 л.c. бензин', 27);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (78, '1.8 152 л.c. бензин', 27);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (60, '2.7 164 л.c. бензин', 23);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (58, '2.4d 150 л.c. дизель', 23);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (59, '2.4d 150 л.c. дизель', 23);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (90, '2.0d 140 л.c. дизель', 34);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (61, '2.8d 177 л.c. дизель', 23);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (62, '4.0 238 л.c. бензин', 23);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (40, '1.2 116 л.c. бензин', 17);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (41, '1.8h 98 л.c. гибрид', 17);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (42, '2.0h 153 л.c. гибрид', 17);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (6, '2.0 306 л.c. бензин', 7);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (21, '2.0 156 л.c. бензин', 13);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (14, '2.5 218 л.c. бензин', 14);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (68, '3.6 300 л.c. бензин', 26);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (69, '2.0 210 л.c. бензин', 26);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (70, '1.8 152 л.c. бензин', 26);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (86, '1.4 125 л.c. бензин', 37);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (22, '2.0 156 л.c. бензин', 14);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (109, '2.0 245 л.c. бензин', 4);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (110, '2.0d 190 л.c. дизель', 4);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (52, '1.5 110 л.c. бензин', 22);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (53, '1.5 105 л.c. бензин', 22);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (71, '2.0 190 л.c. бензин', 31);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (72, '1.4 150 л.c. бензин', 31);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (107, '2.0d 231 л.c. дизель', 1);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (7, '1.5 136 л.c. бензин', 6);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (34, '2.0d 190 л.c. дизель', 8);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (33, '2.0d 150 л.c. дизель', 8);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (16, '2.0d 177 л.c. дизель', 12);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (43, '1.6 122 л.c. бензин', 16);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (20, '2.0 156 л.c. бензин', 12);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (105, '2.0d 231 л.c. дизель', 2);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (65, '3.0d 91 л.c. дизель', 25);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (66, '2.7 145 л.c. бензин', 25);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (67, '2.0 110 л.c. бензин', 25);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (91, '1.4 122 л.c. бензин', 34);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (87, '2.0d 150 л.c. дизель', 36);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (88, '2.0d 184 л.c. дизель', 36);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (89, '1.8 180 л.c. бензин', 36);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (56, '1.3 101 л.c. бензин', 21);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (57, '1.6 124 л.c. бензин', 21);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (18, '2.0d 177 л.c. дизель', 14);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (76, '2.0 210 л.c. бензин', 30);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (108, '2.0 245 л.c. бензин', 3);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (32, '2.0 156 л.c. бензин', 9);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (29, '2.0d 150 л.c. дизель', 11);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (28, '1.5d 116 л.c. дизель', 11);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (27, '2.0d 190 л.c. дизель', 11);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (5, '2.0d 190 л.c. дизель', 7);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (73, '1.4 122 л.c. бензин', 29);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (12, '2.5 218 л.c. бензин', 12);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (64, '2.5d 144 л.c. дизель', 24);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (3, '1.5 140 л.c. бензин', 7);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (26, '1.5 136 л.c. бензин', 11);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (44, '1.3 95 л.c. бензин', 20);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (45, '1.5h 74 л.c. гибрид', 20);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (46, '1.5 103 л.c. бензин', 20);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (47, '1.5 109 л.c. бензин', 20);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (9, '1.5 136 л.c. бензин', 5);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (10, '2.0 231 л.c. бензин', 5);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (11, '2.0 192 л.c. бензин', 5);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (96, '1.2 85 л.c. бензин', 33);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (97, '1.4 122 л.c. бензин', 33);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (98, '1.6 102 л.c. бензин', 33);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (74, '1.8 152 л.c. бензин', 29);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (75, '2.0 210 л.c. бензин', 29);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (55, '1.8 136 л.c. бензин', 22);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (54, '1.8 125 л.c. бензин', 22);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (4, '2.0d 150 л.c. дизель', 7);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (85, '1.4 150 л.c. бензин', 37);
INSERT INTO public.engine (id, name, body_gen_id) VALUES (104, '2.0d 190 л.c. дизель', 2);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (1, 2, 11);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (2, 3, 10);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (3, 2, 2);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (4, 2, 3);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (5, 3, 3);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (6, 2, 4);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (7, 2, 5);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (8, 3, 5);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (9, 3, 6);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (10, 2, 7);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (11, 2, 8);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (12, 2, 9);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (13, 2, 12);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (14, 3, 12);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (15, 3, 13);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (17, 3, 14);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (18, 2, 14);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (16, 2, 13);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (19, 2, 15);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (20, 3, 15);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (21, 2, 16);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (22, 2, 17);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (23, 2, 18);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (24, 2, 19);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (25, 2, 20);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (26, 2, 21);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (27, 2, 22);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (28, 2, 23);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (29, 2, 24);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (30, 2, 25);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (31, 3, 25);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (32, 2, 26);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (33, 2, 27);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (34, 3, 27);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (35, 2, 28);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (36, 2, 29);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (37, 2, 30);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (38, 2, 31);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (39, 3, 31);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (40, 2, 32);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (41, 2, 33);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (42, 2, 34);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (43, 3, 34);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (44, 2, 35);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (45, 1, 36);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (46, 1, 37);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (47, 1, 38);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (48, 1, 39);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (49, 1, 40);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (50, 1, 41);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (51, 1, 42);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (52, 1, 43);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (53, 1, 44);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (54, 1, 45);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (55, 1, 46);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (56, 1, 47);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (57, 1, 48);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (58, 1, 49);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (59, 1, 50);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (60, 1, 51);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (61, 1, 52);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (62, 1, 53);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (63, 1, 54);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (64, 1, 55);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (65, 1, 56);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (66, 1, 57);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (67, 3, 58);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (68, 3, 59);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (69, 3, 60);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (70, 3, 61);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (71, 3, 62);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (72, 3, 63);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (73, 3, 64);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (74, 3, 65);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (75, 3, 66);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (76, 3, 67);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (77, 3, 68);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (78, 1, 69);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (79, 1, 70);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (80, 3, 71);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (81, 1, 71);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (82, 1, 72);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (83, 1, 73);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (84, 1, 74);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (85, 1, 75);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (86, 3, 75);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (87, 1, 76);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (88, 3, 76);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (89, 1, 77);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (90, 1, 78);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (91, 1, 79);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (92, 1, 80);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (93, 1, 81);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (94, 1, 82);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (95, 1, 83);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (96, 1, 84);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (97, 1, 85);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (98, 1, 86);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (99, 1, 87);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (100, 3, 88);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (101, 3, 89);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (102, 1, 90);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (103, 1, 91);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (104, 1, 92);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (105, 1, 93);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (106, 1, 94);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (107, 1, 95);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (108, 1, 96);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (109, 1, 97);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (110, 1, 98);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (111, 1, 99);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (112, 1, 100);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (113, 1, 101);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (114, 1, 102);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (115, 1, 103);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (116, 2, 104);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (117, 3, 105);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (118, 2, 106);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (119, 3, 107);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (120, 3, 108);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (121, 3, 109);
INSERT INTO public.drive_ref (id, drive_id, engine_id) VALUES (122, 2, 110);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (1, 2, 2);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (2, 1, 3);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (3, 1, 4);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (4, 1, 5);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (5, 1, 6);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (6, 1, 7);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (7, 1, 8);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (8, 1, 9);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (9, 1, 10);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (10, 1, 11);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (11, 1, 12);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (12, 1, 13);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (13, 1, 14);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (14, 1, 15);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (15, 1, 16);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (16, 1, 17);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (17, 1, 18);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (18, 1, 19);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (19, 1, 20);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (20, 1, 21);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (21, 1, 22);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (22, 1, 23);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (23, 1, 24);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (24, 1, 25);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (25, 1, 26);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (26, 1, 27);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (27, 1, 28);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (28, 1, 29);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (29, 1, 30);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (30, 1, 31);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (31, 1, 32);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (32, 1, 33);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (33, 1, 34);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (34, 1, 35);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (35, 2, 36);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (36, 1, 37);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (37, 1, 38);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (38, 1, 39);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (39, 1, 40);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (40, 1, 41);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (41, 1, 42);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (42, 1, 43);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (43, 1, 44);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (44, 1, 45);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (45, 1, 46);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (46, 1, 47);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (47, 2, 48);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (48, 2, 49);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (49, 1, 50);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (50, 1, 51);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (51, 1, 52);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (52, 1, 53);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (53, 1, 54);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (54, 1, 55);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (55, 1, 56);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (56, 1, 57);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (57, 1, 58);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (58, 1, 59);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (59, 1, 60);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (60, 1, 61);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (61, 1, 62);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (62, 1, 63);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (63, 1, 64);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (64, 1, 65);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (65, 1, 66);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (66, 1, 67);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (67, 1, 68);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (68, 1, 69);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (69, 1, 70);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (70, 1, 71);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (71, 1, 72);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (72, 1, 73);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (73, 1, 74);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (74, 1, 75);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (75, 1, 76);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (76, 1, 77);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (77, 1, 78);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (78, 1, 79);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (79, 1, 80);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (80, 1, 81);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (81, 1, 82);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (82, 1, 83);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (83, 1, 84);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (84, 1, 85);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (85, 1, 86);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (86, 1, 87);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (87, 1, 88);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (88, 1, 89);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (89, 1, 90);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (90, 1, 91);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (91, 1, 92);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (92, 1, 93);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (93, 1, 94);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (94, 1, 95);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (95, 1, 96);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (96, 1, 97);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (97, 1, 98);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (98, 1, 99);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (99, 1, 100);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (100, 1, 101);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (101, 1, 102);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (102, 1, 103);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (103, 1, 104);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (104, 1, 105);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (105, 1, 106);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (106, 1, 107);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (107, 1, 108);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (108, 1, 109);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (109, 1, 110);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (110, 2, 8);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (111, 2, 9);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (112, 2, 37);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (113, 2, 38);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (114, 2, 39);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (115, 2, 40);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (116, 2, 41);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (117, 2, 42);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (118, 2, 43);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (119, 2, 44);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (120, 2, 45);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (121, 2, 46);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (122, 2, 47);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (123, 3, 37);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (124, 3, 38);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (125, 3, 39);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (126, 3, 40);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (127, 3, 41);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (128, 3, 42);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (129, 3, 43);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (130, 3, 44);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (131, 3, 45);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (132, 3, 46);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (133, 3, 47);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (134, 2, 51);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (135, 2, 52);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (136, 2, 53);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (137, 2, 54);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (138, 2, 55);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (139, 2, 56);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (140, 2, 57);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (141, 2, 83);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (142, 2, 86);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (143, 2, 91);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (144, 2, 93);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (145, 2, 94);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (146, 2, 95);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (147, 2, 96);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (148, 2, 97);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (149, 2, 98);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (150, 2, 99);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (151, 2, 100);
INSERT INTO public.gear_box_ref (id, gear_box_id, engine_id) VALUES (152, 2, 101);
