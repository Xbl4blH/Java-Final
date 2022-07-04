DROP TABLE IF EXISTS Customer CASCADE;
DROP TABLE IF EXISTS City CASCADE;
DROP TABLE IF EXISTS PaymentCard CASCADE;
DROP TABLE IF EXISTS Checks CASCADE;
DROP TABLE IF EXISTS  Firm CASCADE;
DROP TABLE IF EXISTS Station CASCADE;
DROP TABLE IF EXISTS Manager CASCADE;
DROP TABLE IF EXISTS  TelephoneNumber CASCADE;
DROP TABLE IF EXISTS StationType CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS StationManager CASCADE;

CREATE TABLE Users (
                       user_id serial primary key,
                       login varchar(50),
                       pasword varchar(1000),
                       user_role varchar(50),
                       status boolean
);


CREATE TABLE City (
                      city_id serial primary key,
                      name varchar(50),
                      coefficient float,
                      population int

);

CREATE TABLE Firm (
                      firm_id serial primary key,
                      name varchar(50),
                      workers int
);

CREATE TABLE StationType (
                             station_type_id serial primary key,
                             name varchar(50),
                             price int
);

CREATE TABLE Manager (
                         manager_id serial primary key,
                         first_name varchar(50),
                         last_name varchar(50)

);

CREATE TABLE TelephoneNumber (
                                 telephone_number_id serial primary key,
                                 manager_id int references manager(manager_id) on delete cascade,
                                 number varchar(12),
                                 operator varchar(50)
);

CREATE TABLE  Station(
                         station_id serial primary key,
                         firm_id int references firm(firm_id) on delete cascade,
                         station_type_id int references stationtype(station_type_id) on delete cascade,
                         max_performance int,
                         is_active boolean
);

CREATE TABLE Customer(
                         customer_id serial primary key,
                         station_id int references station(station_id) on delete cascade,
                         city_id int references city(city_id) on delete cascade,
                         first_name varchar(50),
                         last_name varchar(50),
                         consumption int
);

CREATE TABLE Checks(
                       checks_id serial primary key,
                       customer_id int references customer(customer_id)  on delete cascade,
                       month int,
                       price int
);

CREATE TABLE PaymentCard(
                            payment_card_id serial primary key,
                            customer_id int references customer(customer_id) on delete cascade,
                            number varchar(20),
                            cvv int,
                            valid_thru boolean
);

CREATE TABLE StationManager(
                               station_manager_id serial primary key,
                               station_id int references Station(station_id) on delete cascade,
                               manager_id int references Manager(manager_id) on delete cascade
);
