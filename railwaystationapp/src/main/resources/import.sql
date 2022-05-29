insert into role(id,name) values (1,'ROLE_USER');
insert into role(id,name) values (2,'ROLE_ADMIN');

insert into user(id, account_not_expired, account_not_locked, balance, credentials_not_expired,email, enabled,firstname,lastname, password, user_type,username) values(1, 1, 1, 0, 1, 'admin@yahoo.com', 1, 'admin', 'admin', '$2a$10$dakKzDCZWByjKT/ib2XWueN7b2qwhA1ipuOm.pRbuuXd8b2KZkV/y', 'NORMAL', 'admin');
insert into user_roles (user_id,role_id) values(1,1);
insert into user_roles (user_id,role_id) values(1,2);

insert into company (id, identification_number,name) values (1,'RO1382BSD','CFR');
insert into company (id, identification_number,name) values (2,'RO4382SAU','CFR CALATORI');
insert into company (id, identification_number,name) values (3,'RO8372HDS','TRANSPORT FEROVIAR PRIVAT');
insert into company (id, identification_number,name) values (4,'RO1234DSD','REGIO CALATORI');


insert into train (id,code,fuel_type, number_of_seats, train_type) values(1,'TR239112','DIESEL',318,'REGIO');
insert into train (id,code,fuel_type, number_of_seats, train_type) values(2,'TR283244','DIESEL',123,'INTERCITY');
insert into train (id,code,fuel_type, number_of_seats, train_type) values(3,'TR238572','HYBRID',224,'INTERREGIO');
insert into train (id,code,fuel_type, number_of_seats, train_type) values(4,'TR242585','HYBRID',352,'EURONIGHT');
insert into train (id,code,fuel_type, number_of_seats, train_type) values(5,'TR231243','ELECTRIC',130,'EURONIGHT');
insert into train (id,code,fuel_type, number_of_seats, train_type) values(6,'TR423858','ELECTRIC',272,'INTERREGIO_NIGHT');

insert into address(id,city,district,number,street,zipcode) values(1, 'BUCURESTI', 'BUCURESTI', '12A', 'LOCOTENENT', '077030');
insert into address(id,city,district,number,street,zipcode) values(2, 'SUCEAVA', 'SUCEAVA', '3482', 'MILCOV', '2383092');
insert into address(id,city,district,number,street,zipcode) values(3, 'Bacau', 'Bacau', '932', 'Binelui', '92831');
insert into address(id,city,district,number,street,zipcode) values(4, 'Constanta', 'Constanta', '382', 'Cazinou', '92831-93');

insert into railway_station(id,name,railway_type,address_id) values(1, 'Gara de nord', 'CENTRAL', 1);
insert into railway_station(id,name,railway_type,address_id) values(2, 'SUCEAVA', 'TERMINAL', 2);
insert into railway_station(id,name,railway_type,address_id) values(3, 'Gara Bacau', 'TERMINAL', 3);
insert into railway_station(id,name,railway_type,address_id) values(4, 'Gara Constanta', 'JUNCTION', 4);


insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (1, '2022-04-24 16:00:00.000000', '2022-04-23 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 3, 1, 4, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (2, '2022-04-26 16:00:00.000000', '2022-04-25 17:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 3, 2, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (3, '2022-04-25 16:00:00.000000', '2022-04-24 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 3, 3, 4, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (4, '2022-04-26 16:00:00.000000', '2022-04-26 13:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 3, 1, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (5, '2022-04-25 16:00:00.000000', '2022-04-24 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 3, 2, 4, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (6, '2022-04-26 16:00:00.000000', '2022-04-26 11:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 3, 3, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (7, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 4, 1, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (8, '2022-04-26 16:00:00.000000', '2022-04-25 13:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 4, 2, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (9, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 4, 3, 2, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (10, '2022-04-26 16:00:00.000000', '2022-04-26 08:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 4, 1, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (11, '2022-04-25 16:00:00.000000', '2022-04-24 23:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 4, 2, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (12, '2022-04-26 16:00:00.000000', '2022-04-26 10:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6,4, 3, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (13, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 2, 1, 4, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (14, '2022-04-26 16:00:00.000000', '2022-04-25 17:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 2, 2, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (15, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 2, 3, 4, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (16, '2022-04-26 16:00:00.000000', '2022-04-26 09:40:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 2, 1, 1, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (17, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 2, 2, 4, 5);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (18, '2022-04-26 16:00:00.000000', '2022-04-25 17:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 2, 3, 1, 5);

insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (19, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 3, 1, 4, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (20, '2022-04-26 16:00:00.000000', '2022-04-25 17:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 3, 2, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (21, '2022-04-25 16:00:00.000000', '2022-04-24 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 3, 3, 4, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (22, '2022-04-26 16:00:00.000000', '2022-04-26 13:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 3, 1, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (23, '2022-04-25 16:00:00.000000', '2022-04-23 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 3, 2, 4, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (24, '2022-04-26 16:00:00.000000', '2022-04-26 13:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 3, 3, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (25, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 4, 1, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (26, '2022-04-26 16:00:00.000000', '2022-04-25 17:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 4, 2, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (27, '2022-04-25 16:00:00.000000', '2022-04-25 14:50:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 4, 3, 2, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (28, '2022-04-26 16:00:00.000000', '2022-04-26 12:20:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 4, 1, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (29, '2022-04-25 16:00:00.000000', '2022-04-24 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 4, 2, 3, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (30, '2022-04-26 16:00:00.000000', '2022-04-26 13:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6,4, 3, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (31, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 2, 1, 4, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (32, '2022-04-26 16:00:00.000000', '2022-04-25 17:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 2, 2, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (33, '2022-04-25 16:00:00.000000', '2022-04-25 14:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 2, 3, 4, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (34, '2022-04-26 16:00:00.000000', '2022-04-26 15:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 2, 1, 1, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (35, '2022-04-25 16:00:00.000000', '2022-04-25 12:00:00.000000', 27, 'NOT_STARTED_YET', 0, 6, 2, 2, 4, 1);
insert into journey(id,arrival_time,departure_time,distance,journey_status,minute_late,ticket_price, arrival_railway_station_id, company_id,departure_railway_station_id,train_id) values (36, '2022-04-26 16:00:00.000000', '2022-04-26 02:00:00.000000', 278, 'NOT_STARTED_YET', 0, 6, 2, 3, 1, 1);


