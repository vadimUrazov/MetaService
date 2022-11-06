CREATE TABLE buscompany.public.bus
(
    id          SERIAL primary key,
    bus_name    varchar(100) NOT NULL,
    place_count int          NOT NULL
);


CREATE TABLE buscompany.public."user"
(
    id         SERIAL primary key,
    surname    varchar(100)        NOT NULL,
    name       varchar(100)        NOT NULL,
    middlename varchar(100) DEFAULT NULL,
    login      varchar(100) unique NOT NULL,
    password   varchar(100)        NOT NULL,
    type       varchar(100)        NOT NULL

);


CREATE TABLE buscompany.public.admin
(
    id       SERIAL primary key,
    id_user  int          NOT NULL,
    position varchar(100) NOT NULL,

    CONSTRAINT adminFk FOREIGN KEY (id_user) REFERENCES "user" (id) ON DELETE CASCADE
);


CREATE TABLE buscompany.public.client
(
    id      SERIAL primary key,
    id_user int          NOT NULL,
    email   varchar(100) NOT NULL,
    phone   varchar(100) NOT NULL,

    CONSTRAINT client_fk FOREIGN KEY (id_user) REFERENCES "user" (id) ON DELETE CASCADE
);


CREATE TABLE buscompany.public.trip
(
    id           SERIAL primary key,
    from_station varchar(100)   NOT NULL,
    to_station   varchar(100)   NOT NULL,
    start        time           NOT NULL,
    duration     time           NOT NULL,
    price        decimal(10, 2) NOT NULL,
    id_bus       int            NOT NULL,
    approved     boolean        NOT NULL,

    CONSTRAINT busFk FOREIGN KEY (id_bus) REFERENCES bus (id) ON DELETE CASCADE
);

CREATE TABLE buscompany.public.day_trip
(
    id         SERIAL primary key,
    id_trip    int  NOT NULL,
    date       date NOT NULL,
    free_count int  NOT NULL,

    CONSTRAINT dayFk FOREIGN KEY (id_trip) REFERENCES trip (id) ON DELETE CASCADE
);



CREATE TABLE buscompany.public."order"
(
    id          SERIAL primary key,
    id_day_trip int            NOT NULL,
    id_client   int            NOT NULL,
    total_price decimal(10, 2) NOT NULL,
    CONSTRAINT trFk FOREIGN KEY (id_day_trip) REFERENCES day_trip (id) ON DELETE CASCADE,
    CONSTRAINT clientFk FOREIGN KEY (id_client) REFERENCES client (id) ON DELETE CASCADE
);


CREATE TABLE buscompany.public.passenger
(
    id         SERIAL primary key,
    first_name varchar(100) NOT NULL,
    last_name  varchar(100) NOT NULL,
    passport   int          NOT NULL,
    id_order   int          NOT NULL,
    CONSTRAINT orderFk FOREIGN KEY (id_order) REFERENCES "order" (id) ON DELETE CASCADE
);


CREATE TABLE buscompany.public.place
(
    id           SERIAL primary key,
    number       int NOT NULL,
    id_day_trip  int NOT NULL,
    id_passenger int DEFAULT NULL,

    CONSTRAINT placeFk FOREIGN KEY (id_day_trip) REFERENCES day_trip (id) ON DELETE CASCADE,
    CONSTRAINT passFk FOREIGN KEY (id_passenger) REFERENCES passenger (id) ON DELETE CASCADE
);

INSERT INTO buscompany.public.bus(bus_name, place_count)
VALUES ('Renault', 40);
INSERT INTO buscompany.public.bus(bus_name, place_count)
VALUES ('Pazik', 25);
INSERT INTO buscompany.public.bus(bus_name, place_count)
VALUES ('Honday', 30);
INSERT INTO buscompany.public.bus(bus_name, place_count)
VALUES ('Toyota', 35);
INSERT INTO buscompany.public.bus(bus_name, place_count)
VALUES ('Lada', 1);
INSERT INTO buscompany.public.bus(bus_name, place_count)
VALUES ('Audi', 3);


