CREATE TABLE cars
(
    id bigint unique,
    name varchar primary key,
    model varchar,
    price numeric
);

CREATE TABLE peoples
(
    id bigint unique,
    name varchar primary key,
    age integer,
    avtoCard integer,
    cars_id bigint REFERENCES peoples (id)
);