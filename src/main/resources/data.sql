INSERT INTO region(`region_id`,`name`) VALUES (1,'AMAZONAS');
INSERT INTO region(`region_id`,`name`) VALUES (2,'ANCASH');
INSERT INTO region(`region_id`,`name`) VALUES (3,'APURIMAC');
INSERT INTO region(`region_id`,`name`) VALUES (4,'AREQUIPA');
INSERT INTO region(`region_id`,`name`) VALUES (5,'AYACUCHO');
INSERT INTO region(`region_id`,`name`) VALUES (6,'CAJAMARCA');
INSERT INTO region(`region_id`,`name`) VALUES (7,'CALLAO');
INSERT INTO region(`region_id`,`name`) VALUES (8,'CUSCO');
INSERT INTO region(`region_id`,`name`) VALUES (9,'HUANCAVELICA');
INSERT INTO region(`region_id`,`name`) VALUES (10,'HUANUCO');
INSERT INTO region(`region_id`,`name`) VALUES (11,'ICA');
INSERT INTO region(`region_id`,`name`) VALUES (12,'JUNIN');
INSERT INTO region(`region_id`,`name`) VALUES (13,'LA LIBERTAD');
INSERT INTO region(`region_id`,`name`) VALUES (14,'LAMBAYEQUE');
INSERT INTO region(`region_id`,`name`) VALUES (15,'LIMA');
INSERT INTO region(`region_id`,`name`) VALUES (16,'LORETO');
INSERT INTO region(`region_id`,`name`) VALUES (17,'MADRE DE DIOS');
INSERT INTO region(`region_id`,`name`) VALUES (18,'MOQUEGUA');
INSERT INTO region(`region_id`,`name`) VALUES (19,'PASCO');
INSERT INTO region(`region_id`,`name`) VALUES (20,'PUNO');
INSERT INTO region(`region_id`,`name`) VALUES (21,'SAN MARTIN');
INSERT INTO region(`region_id`,`name`) VALUES (22,'TACNA');
INSERT INTO region(`region_id`,`name`) VALUES (23,'TUMBES');
INSERT INTO region(`region_id`,`name`) VALUES (24,'UCAYALI');





INSERT INTO category(`category_id`, `name`) VALUES (1,'CARNES');
INSERT INTO category(`category_id`, `name`) VALUES (2,'VERDURAS');
INSERT INTO category(`category_id`, `name`) VALUES (3,'MARISCOS');
INSERT INTO category(`category_id`, `name`) VALUES (4,'TUBERCULOS');
INSERT INTO category(`category_id`, `name`) VALUES (5,'FRUTAS');

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


INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (1,'','Diabetes Tipo 1');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (2,'','Diabetes Tipo 2');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (3,'','Diabetes Tipo 3');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (4,'','Diabetes Tipo 4');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (5,'','Diabetes Tipo 5');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (6,'','Cálculos Renales');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (7,'','Cáncer');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (8,'','Asma');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (9,'','Enfermedad de Crohn');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (10,'','Desnutrición');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (11,'','Anemía');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (12,'','Anorexia');
INSERT INTO `illnesses` (`illnesses_id`,`description`,`name`)VALUES (13,'','Bulimia');


SET FOREIGN_KEY_CHECKS=0;