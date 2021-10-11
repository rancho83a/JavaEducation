CREATE DATABASE test;
use test;
#1
CREATE TABLE people (
person_id INT ,
first_name VARCHAR(30) NOT NULL,
salary DECIMAL(10,2) NOT NULL,
passport_id INT NOT NULL UNIQUE
);

CREATE TABLE passports(
passport_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
passport_number VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO `people` 
VALUES 
(1, 'Roberto', 43300.00, 102),
(2, 'Tom', 56100.00, 103),
(3, 'Yana', 60200.00, 101);

ALTER TABLE `passports`  AUTO_INCREMENT=101;
INSERT INTO `passports`(`passport_number`)
VALUES 
('N34FG21B'),
( 'K65LO4R7'),
('ZE657QP2');

ALTER TABLE `people` 
MODIFY COLUMN `person_id` INT NOT NULL AUTO_INCREMENT ,
ADD PRIMARY KEY (`person_id`);


ALTER TABLE `people`
ADD CONSTRAINT fk_people_passports
FOREIGN KEY (`passport_id`) 
REFERENCES passports(`passport_id`);


#2
CREATE DATABASE cars;
use cars;

CREATE TABLE `manufacturers` (
manufacturer_id INt PRIMARY KEY NOT NULL AUTO_INCREMENT,
name VARCHAR(40) NOT NULL,
established_on DATE NOT NULL
);

INSERT INTO `manufacturers`
VALUES 
(1, 'BMW', '1916-03-01'),
(2, 'Tesla', '2003-01-01'),
(3, 'Lada', '1966-05-01');


CREATE TABLE models (
model_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
manufacturer_id INT NOT NULL,
CONSTRAINT fk_models_manufactures FOREIGN KEY (manufacturer_id) REFERENCES `manufacturers`(`manufacturer_id`)
) AUTO_INCREMENT=101;

INSERT INTO models (`name`, `manufacturer_id`)
VALUES
('X1', 1),
('i6', 1),
( 'Model S', 2),
('Model X', 2),
('Model 3',2),
('Nova',3);

#3
CREATE DATABASE  task3;
use task3;

CREATE TABLE students(
student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);
INSERT INTO students(`name`)
VALUES 
('Mila'),
('Toni'),
('Ron');

CREATE TABLE exams(
exam_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
)AUTO_INCREMENT=101;

INSERT INTO exams(`name`)
VALUES 
('Spring MVC'),
('Neo4j'),
('Oracle 11g');

CREATE TABLE students_exams (
student_id INT,
exam_id INT,

CONSTRAINT pk_student_exam
PRIMARY KEY (student_id, exam_id),

CONSTRAINT fk_student_exams_students
FOREIGN KEY (`student_id`)
REFERENCES students(`student_id`),

CONSTRAINT fk_student_exams_exams
FOREIGN KEY (`exam_id`)
REFERENCES exams(`exam_id`)
);

INSERT INTO students_exams 
VALUES
(1,101),
(1,102),
(2,101),
(3,103),
(2,102),
(2,103);


#4
CREATE DATABASE task4;
USE task4;

CREATE table teachers(
teacher_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) not NULL,
manager_id INT
) AUTO_INCREMENT=101;

INSERT INTO teachers(`name`, `manager_id`)
VALUES 

('John', null),
('Maya', 106),
('Silvia', 106),
('Ted', 105),
('Mark', 101),
('Greta', 101);

ALTER TABLE teachers
ADD CONSTRAINT fk_teachers_managers
FOREIGN KEY (manager_id) REFERENCES teachers(teacher_id);


#5
CREATE DATABASE task5;
use task5;


CREATE TABLE cities(
city_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE customers(
customer_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
birthday DATE,
city_id INT,
CONSTRAINT fk_customers_cities
FOREIGN KEY (city_id) REFERENCES cities(city_id)
);

CREATE TABLE orders(
order_id INT PRIMARY KEY AUTO_INCREMENT,
customer_id INT,
CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE item_types(
item_type_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE items(
item_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
item_type_id INT,
CONSTRAINT fk_items_item_types
FOREIGN KEY (item_type_id) REFERENCES item_types(item_type_id)
);

CREATE TABLE order_items(
order_id INT,
item_id INT,
CONSTRAINT pk_order_items
PRIMARY KEY (order_id, item_id),
CONSTRAINT fk_order_items_orders
FOREIGN KEY (order_id) REFERENCES orders(order_id),

CONSTRAINT fk_order_items_items
FOREIGN KEY (item_id) REFERENCES items(item_id)
);


#6
CREATE DATABASE university;
use university;


CREATE TABLE majors(
major_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE students(
student_id INT PRIMARY KEY AUTO_INCREMENT,
`student_number` VARCHAR(12),
`student_name` VARCHAR(50),
major_id INT,
CONSTRAINT fk_students_major
FOREIGN KEY (major_id) REFERENCES majors(major_id)
);

CREATE TABLE payments(
payment_id INT PRIMARY KEY AUTO_INCREMENT,
`payment_date` DATE,
payment_amount DECIMAL(8,2),
student_id INT,
CONSTRAINT fk_payments_students
FOREIGN KEY (student_id) REFERENCES students(student_id)
);

CREATE TABLE subjects(
subject_id INT PRIMARY KEY AUTO_INCREMENT,
`subject_name` VARCHAR(50)
);

CREATE TABLE agenda(
student_id INT,
subject_id INT,
CONSTRAINT pk_agenda
PRIMARY KEY (student_id,subject_id),
CONSTRAINT fk_agenda_students
FOREIGN KEY (student_id) REFERENCES students(student_id),

CONSTRAINT fk_agenda_subjects
FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);


#7

SELECT m.mountain_range, p.peak_name, p.elevation as peak_elevation FROM mountains as m
JOIN peaks as p
ON p.mountain_id=m.id 
WHERE mountain_range='Rila'
ORDER BY peak_elevation DESC;





















