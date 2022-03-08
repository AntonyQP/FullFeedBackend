INSERT INTO region(`name`) VALUES ('AMAZONAS');
INSERT INTO region(`name`) VALUES ('ANCASH');
INSERT INTO region(`name`) VALUES ('APURIMAC');
INSERT INTO region(`name`) VALUES ('AREQUIPA');
INSERT INTO region(`name`) VALUES ('AYACUCHO');
INSERT INTO region(`name`) VALUES ('CAJAMARCA');
INSERT INTO region(`name`) VALUES ('CALLAO');
INSERT INTO region(`name`) VALUES ('CUSCO');
INSERT INTO region(`name`) VALUES ('HUANCAVELICA');
INSERT INTO region(`name`) VALUES ('HUANUCO');
INSERT INTO region(`name`) VALUES ('ICA');
INSERT INTO region(`name`) VALUES ('JUNIN');
INSERT INTO region(`name`) VALUES ('LA LIBERTAD');
INSERT INTO region(`name`) VALUES ('LAMBAYEQUE');
INSERT INTO region(`name`) VALUES ('LIMA');
INSERT INTO region(`name`) VALUES ('LORETO');
INSERT INTO region(`name`) VALUES ('MADRE DE DIOS');
INSERT INTO region(`name`) VALUES ('MOQUEGUA');
INSERT INTO region(`name`) VALUES ('PASCO');
INSERT INTO region(`name`) VALUES ('PUNO');
INSERT INTO region(`name`) VALUES ('SAN MARTIN');
INSERT INTO region(`name`) VALUES ('TACNA');
INSERT INTO region(`name`) VALUES ('TUMBES');
INSERT INTO region(`name`) VALUES ('UCAYALI');





INSERT INTO category(`name`) VALUES ('CEREALES');
INSERT INTO category(`name`) VALUES ('CARNES');
INSERT INTO category(`name`) VALUES ('VEGETALES');
INSERT INTO category(`name`) VALUES ('FRUTAS');
INSERT INTO category(`name`) VALUES ('LACTEOS');
INSERT INTO category(`name`) VALUES ('OTROS');

INSERT INTO preferences (`name`,`category_id`) VALUES ('Arroz', 4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Pollo', 14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Carne', 14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Pescado',14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Atun', 14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Huevo', 54);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Pavo', 14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Cerdo', 14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Jamon', 14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('Tofu', 54);







INSERT INTO personal_treatment(`active`, `end_date`, `start_date`, `doctor_id`, `patient_id`)
VALUES (1,now(),now(), 4,4);



INSERT INTO nutritional_plan (`meal_date`,`personal_treatments_id`,`is_active`)
VALUES (now(),4,0);


SET FOREIGN_KEY_CHECKS=0;