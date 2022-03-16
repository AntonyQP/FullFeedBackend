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





INSERT INTO category(`name`) VALUES ('CARNES');
INSERT INTO category(`name`) VALUES ('VERDURAS');
INSERT INTO category(`name`) VALUES ('MARISCOS');
INSERT INTO category(`name`) VALUES ('TUBERCULOS');
INSERT INTO category(`name`) VALUES ('FRUTAS');

INSERT INTO preferences (`name`,`category_id`) VALUES ('CERDO',     4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CABRITO',   4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('RES',       4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PESCADO',   4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CORDERO',   4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CUY',       4);

INSERT INTO preferences (`name`,`category_id`) VALUES ('ZAPALLO',   14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CHOCLO',    14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('APIO',      14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('BETERRAGA', 14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('TOMATE',    14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('ACEITUNA',  14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CULANTRO',  14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('BROCOLI',   14);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CAIGUA',    14);

INSERT INTO preferences (`name`,`category_id`) VALUES ('CANGREJO',   24);
INSERT INTO preferences (`name`,`category_id`) VALUES ('LANGOSTINO', 24);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CHORO',      24);
INSERT INTO preferences (`name`,`category_id`) VALUES ('ALMEJA',     24);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CAMARON',    24);

INSERT INTO preferences (`name`,`category_id`) VALUES ('CAMOTE',    34);
INSERT INTO preferences (`name`,`category_id`) VALUES ('OLLUCO',    34);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PAPA',      34);
INSERT INTO preferences (`name`,`category_id`) VALUES ('YUCA',      34);

INSERT INTO preferences (`name`,`category_id`) VALUES ('MANZANA',   44);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PIÑA',      44);
INSERT INTO preferences (`name`,`category_id`) VALUES ('NARANJA',   44);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PAPAYA',    44);
INSERT INTO preferences (`name`,`category_id`) VALUES ('MANDARINA', 44);
INSERT INTO preferences (`name`,`category_id`) VALUES ('TUNA',      44);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PLATANO',   44);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CARAMBOLA', 44);



INSERT INTO personal_treatment(`active`, `end_date`, `start_date`, `doctor_id`, `patient_id`)
VALUES (1,now(),now(), 4,4);



INSERT INTO nutritional_plan (`meal_date`,`personal_treatments_id`,`is_active`)
VALUES (now(),4,0);


SET FOREIGN_KEY_CHECKS=0;