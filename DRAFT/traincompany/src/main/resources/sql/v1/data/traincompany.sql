CREATE TABLE traincompany.public.train
(
    id          SERIAL primary key,
    train_name  varchar(100) NOT NULL,
    place_count int          NOT NULL,
    car         int          NOT NULL
);


CREATE TABLE traincompany.public."user"
(
    id         SERIAL primary key,
    surname    varchar(100)        NOT NULL,
    name       varchar(100)        NOT NULL,
    middlename varchar(100) DEFAULT NULL,
    login      varchar(100) unique NOT NULL,
    password   varchar(100)        NOT NULL,
    type       varchar(100)        NOT NULL

);


CREATE TABLE traincompany.public.admin
(
    id       SERIAL primary key,
    id_user  int          NOT NULL,
    position varchar(100) NOT NULL,

    CONSTRAINT adminFk FOREIGN KEY (id_user) REFERENCES "user" (id) ON DELETE CASCADE
);


CREATE TABLE traincompany.public.client
(
    id      SERIAL primary key,
    id_user int          NOT NULL,
    email   varchar(100) NOT NULL,
    phone   varchar(100) NOT NULL,

    CONSTRAINT client_fk FOREIGN KEY (id_user) REFERENCES "user" (id) ON DELETE CASCADE
);


CREATE TABLE traincompany.public.trip
(
    id                SERIAL primary key,
    from_station      varchar(100)   NOT NULL,
    duration_stations varchar(100) DEFAULT NULL,
    to_station        varchar(100)   NOT NULL,
    start             time           NOT NULL,
    duration          time           NOT NULL,
    price             decimal(10, 2) NOT NULL,
    id_train          int            NOT NULL,
    approved          boolean        NOT NULL,

    CONSTRAINT trainFk FOREIGN KEY (id_train) REFERENCES train (id) ON DELETE CASCADE
);

CREATE TABLE traincompany.public.day_trip
(
    id         SERIAL primary key,
    id_trip    int  NOT NULL,
    date       date NOT NULL,
    free_count int  NOT NULL,

    CONSTRAINT dayFk FOREIGN KEY (id_trip) REFERENCES trip (id) ON DELETE CASCADE
);


CREATE TABLE traincompany.public."order"
(
    id          SERIAL primary key,
    id_day_trip int            NOT NULL,
    id_client   int            NOT NULL,
    total_price decimal(10, 2) NOT NULL,
    CONSTRAINT trFk FOREIGN KEY (id_day_trip) REFERENCES day_trip (id) ON DELETE CASCADE,
    CONSTRAINT clientFk FOREIGN KEY (id_client) REFERENCES client (id) ON DELETE CASCADE
);


CREATE TABLE traincompany.public.passenger
(
    id         SERIAL primary key,
    first_name varchar(100) NOT NULL,
    last_name  varchar(100) NOT NULL,
    passport   int          NOT NULL,
    id_order   int          NOT NULL,
    CONSTRAINT orderFk FOREIGN KEY (id_order) REFERENCES "order" (id) ON DELETE CASCADE
);

CREATE TABLE traincompany.public."order_passengers"
(
    id           SERIAL primary key,
    "order_id"     int NOT NULL,
    passengers_id int DEFAULT NULL,
    CONSTRAINT ordFk FOREIGN KEY ("order_id") REFERENCES "order" (id) ON DELETE CASCADE,
    CONSTRAINT pasFk FOREIGN KEY (passengers_id) REFERENCES passenger (id) ON DELETE CASCADE

);


CREATE TABLE traincompany.public.place
(
    id           SERIAL primary key,
    number       int NOT NULL,
    car          int NOT NULL,
    id_day_trip  int NOT NULL,
    id_passenger int DEFAULT NULL,

    CONSTRAINT placeFk FOREIGN KEY (id_day_trip) REFERENCES day_trip (id) ON DELETE CASCADE,
    CONSTRAINT passFk FOREIGN KEY (id_passenger) REFERENCES passenger (id) ON DELETE CASCADE
);

INSERT INTO traincompany.public.train(train_name, place_count, car)
VALUES ('Renault', 40, 3);
INSERT INTO traincompany.public.train(train_name, place_count, car)
VALUES ('Pazik', 25, 3);
INSERT INTO traincompany.public.train(train_name, place_count, car)
VALUES ('Honday', 30, 3);
INSERT INTO traincompany.public.train(train_name, place_count, car)
VALUES ('Toyota', 35, 1);
INSERT INTO traincompany.public.train(train_name, place_count, car)
VALUES ('Lada', 1, 1);
INSERT INTO traincompany.public.train(train_name, place_count, car)
VALUES ('Audi', 3, 1);

