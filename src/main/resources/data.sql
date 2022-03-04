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


INSERT INTO nutritional_plan (`meal_date`, `personal_treatments_id`)
VALUES (now(), 4);