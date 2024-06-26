CREATE TABLE shipcompany.public.ship
(
    id          SERIAL primary key,
    ship_name   varchar(100) NOT NULL,
    place_count int          NOT NULL
);


CREATE TABLE shipcompany.public."user"
(
    id         SERIAL primary key,
    surname    varchar(100)        NOT NULL,
    name       varchar(100)        NOT NULL,
    middlename varchar(100) DEFAULT NULL,
    login      varchar(100) unique NOT NULL,
    password   varchar(100)        NOT NULL,
    type       varchar(100)        NOT NULL

);


CREATE TABLE shipcompany.public.admin
(
    id       SERIAL primary key,
    id_user  int          NOT NULL,
    position varchar(100) NOT NULL,

    CONSTRAINT adminFk FOREIGN KEY (id_user) REFERENCES "user" (id) ON DELETE CASCADE
);


CREATE TABLE shipcompany.public.client
(
    id      SERIAL primary key,
    id_user int          NOT NULL,
    email   varchar(100) NOT NULL,
    phone   varchar(100) NOT NULL,

    CONSTRAINT client_fk FOREIGN KEY (id_user) REFERENCES "user" (id) ON DELETE CASCADE
);


CREATE TABLE shipcompany.public.trip
(
    id           SERIAL primary key,
    from_station varchar(100)   NOT NULL,
    to_station   varchar(100)   NOT NULL,
    start        time           NOT NULL,
    duration     time           NOT NULL,
    price        decimal(10, 2) NOT NULL,
    id_ship      int            NOT NULL,
    approved     boolean        NOT NULL,

    CONSTRAINT shipFk FOREIGN KEY (id_ship) REFERENCES ship (id) ON DELETE CASCADE
);

CREATE TABLE shipcompany.public.day_trip
(
    id         SERIAL primary key,
    id_trip    int  NOT NULL,
    date       date NOT NULL,
    free_count int  NOT NULL,

    CONSTRAINT dayFk FOREIGN KEY (id_trip) REFERENCES trip (id) ON DELETE CASCADE
);



CREATE TABLE shipcompany.public."order"
(
    id          SERIAL primary key,
    id_day_trip int            NOT NULL,
    id_client   int            NOT NULL,
    total_price decimal(10, 2) NOT NULL,
    CONSTRAINT trFk FOREIGN KEY (id_day_trip) REFERENCES day_trip (id) ON DELETE CASCADE,
    CONSTRAINT clientFk FOREIGN KEY (id_client) REFERENCES client (id) ON DELETE CASCADE
);

CREATE TABLE shipcompany.public.cargo
(
    id         SERIAL primary key,
    cargo_type varchar(100) NOT NULL,
    id_client  int          NOT NULL,
    id_order   int          NOT NULL,
    CONSTRAINT clnFk FOREIGN KEY (id_client) REFERENCES client (id) ON DELETE CASCADE,
    CONSTRAINT ordFk FOREIGN KEY (id_order) REFERENCES "order" (id) ON DELETE CASCADE
);
CREATE TABLE shipcompany.public.passenger
(
    id         SERIAL primary key,
    first_name varchar(100) NOT NULL,
    last_name  varchar(100) NOT NULL,
    passport   int          NOT NULL,
    id_order   int          NOT NULL,
    CONSTRAINT orderFk FOREIGN KEY (id_order) REFERENCES "order" (id) ON DELETE CASCADE
);


CREATE TABLE shipcompany.public.place
(
    id           SERIAL primary key,
    number       int NOT NULL,
    id_day_trip  int NOT NULL,
    id_passenger int DEFAULT NULL,

    CONSTRAINT placeFk FOREIGN KEY (id_day_trip) REFERENCES day_trip (id) ON DELETE CASCADE,
    CONSTRAINT passFk FOREIGN KEY (id_passenger) REFERENCES passenger (id) ON DELETE CASCADE
);

INSERT INTO shipcompany.public.ship(ship_name, place_count)
VALUES ('Step', 40);
INSERT INTO shipcompany.public.ship(ship_name, place_count)
VALUES ('Avrora', 25);
INSERT INTO shipcompany.public.ship(ship_name, place_count)
VALUES ('Honday', 30);
INSERT INTO shipcompany.public.ship(ship_name, place_count)
VALUES ('Toyota', 35);
INSERT INTO shipcompany.public.ship(ship_name, place_count)
VALUES ('Samara', 1);
INSERT INTO shipcompany.public.ship(ship_name, place_count)
VALUES ('Riga', 3);


