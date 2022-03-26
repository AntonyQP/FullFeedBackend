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

INSERT INTO preferences (`name`,`category_id`) VALUES ('CERDO',     1);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CABRITO',   1);
INSERT INTO preferences (`name`,`category_id`) VALUES ('RES',       1);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PESCADO',   1);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CORDERO',   1);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CUY',       1);

INSERT INTO preferences (`name`,`category_id`) VALUES ('ZAPALLO',   2);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CHOCLO',    2);
INSERT INTO preferences (`name`,`category_id`) VALUES ('APIO',      2);
INSERT INTO preferences (`name`,`category_id`) VALUES ('BETERRAGA', 2);
INSERT INTO preferences (`name`,`category_id`) VALUES ('TOMATE',    2);
INSERT INTO preferences (`name`,`category_id`) VALUES ('ACEITUNA',  2);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CULANTRO',  2);
INSERT INTO preferences (`name`,`category_id`) VALUES ('BROCOLI',   2);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CAIGUA',    2);

INSERT INTO preferences (`name`,`category_id`) VALUES ('CANGREJO',   3);
INSERT INTO preferences (`name`,`category_id`) VALUES ('LANGOSTINO', 3);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CHORO',      3);
INSERT INTO preferences (`name`,`category_id`) VALUES ('ALMEJA',     3);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CAMARON',    3);

INSERT INTO preferences (`name`,`category_id`) VALUES ('CAMOTE',    4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('OLLUCO',    4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PAPA',      4);
INSERT INTO preferences (`name`,`category_id`) VALUES ('YUCA',      4);

INSERT INTO preferences (`name`,`category_id`) VALUES ('MANZANA',   5);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PIÑA',      5);
INSERT INTO preferences (`name`,`category_id`) VALUES ('NARANJA',   5);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PAPAYA',    5);
INSERT INTO preferences (`name`,`category_id`) VALUES ('MANDARINA', 5);
INSERT INTO preferences (`name`,`category_id`) VALUES ('TUNA',      5);
INSERT INTO preferences (`name`,`category_id`) VALUES ('PLATANO',   5);
INSERT INTO preferences (`name`,`category_id`) VALUES ('CARAMBOLA', 5);



INSERT INTO personal_treatment(`active`, `end_date`, `start_date`, `doctor_id`, `patient_id`)
VALUES (1,now(),now(), 4,4);



INSERT INTO nutritional_plan (`meal_date`,`personal_treatments_id`,`is_active`)
VALUES (now(),4,0);


SET FOREIGN_KEY_CHECKS=0;