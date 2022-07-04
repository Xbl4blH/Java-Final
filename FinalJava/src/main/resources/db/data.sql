INSERT INTO Users (login, pasword, user_role, status)
VALUES ('aida','$2a$12$x9SDFwh.dQUCA6/IFiOaKe1BOuI3SieRXBXEpjqW1jyRU0MVu.VBm','administrator', true);
-- password: 'Ralan'$2a$12$x9SDFwh.dQUCA6/IFiOaKe1BOuI3SieRXBXEpjqW1jyRU0MVu.VBm

INSERT INTO City (coefficient, population, name)
VALUES (1.2, 1000, 'Almaty'),
       (1.1, 200, 'Kustanai');

INSERT INTO Firm (name, workers)
VALUES ('KazElectro', 20),
       ('GazElectroTermo', 35);

INSERT INTO StationType (name, price)
VALUES ('TEC', 12),
       ('AEC', 7),
       ('GEC', 14),
       ('VEC', 17);

INSERT INTO Station (firm_id, station_type_id, max_performance, is_active)
VALUES (1, 1, 3000, True),
       (1, 2, 4000, True),
       (2, 3, 2000, False),
       (2, 4, 1500, True);

INSERT INTO manager (first_name, last_name)
VALUES ('John', 'Lenon'),
       ('Naruto', 'Uzumaki'),
       ('Ilon', 'Mask');

INSERT INTO telephonenumber (manager_id, number, operator)
VALUES (1, '+77778787334', 'Beeline'),
       (1, '+77475066754', 'Tele2'),
       (2, '+77475665623', 'Tele2'),
       (3, '+77789363647', 'Kcell'),
       (3, '+77078874394', 'Tele2');

INSERT INTO stationmanager (station_id, manager_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 3);

INSERT INTO customer (station_id, city_id, first_name, last_name, consumption)
VALUES (1, 1, 'Elvira', 'Nugmanova', 50),
       (1, 1, 'Anuar', 'Borangaziev', 120),
       (1, 1, 'Alisher', 'Ruziev', 80),
       (2, 1, 'Arystan', 'Kim', 75),
       (2, 1, 'Karim', 'Ilyasov', 100),
       (3, 2, 'Rafael', 'Toizhanov', 110),
       (3, 2, 'Ramazan', 'Bolat', 200),
       (4, 2, 'Davlatbek', 'Ushurbakiev', 50),
       (4, 2, 'Ulan', 'Erikov', 120),
       (4, 2, 'Aida', 'Marat', 200);

INSERT INTO checks (customer_id, month, price)
VALUES (1, 1, 1200),
       (2, 1, 200),
       (3, 1, 450),
       (4, 1, 800),
       (5, 1, 500),
       (6, 1, 340),
       (7, 1, 600),
       (8, 1, 700),
       (9, 1, 230),
       (10, 1, 400),
       (1, 2, 1201),
       (2, 2, 202),
       (3, 2, 453),
       (4, 2, 804),
       (5, 2, 505),
       (6, 2, 346),
       (7, 2, 607),
       (8, 2, 708),
       (9, 2, 239),
       (10, 2, 400);

INSERT INTO paymentcard (customer_id, number, cvv, valid_thru)
VALUES (1, '1234 1234 1234 1111', 311, TRUE),
       (2, '1234 1234 1234 2222', 722, TRUE),
       (3, '1234 1234 1234 3333', 833, TRUE),
       (4, '1234 1234 1234 4444', 244, FALSE),
       (5, '1234 1234 1234 5555', 244, TRUE),
       (6, '1234 1234 1234 6666', 244, TRUE),
       (7, '1234 1234 1234 7777', 244, FALSE),
       (8, '1234 1234 1234 8888', 244, TRUE),
       (9, '1234 1234 1234 9999', 244, FALSE),
       (10, '1234 1234 1234 0000', 244, TRUE);