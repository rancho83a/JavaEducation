clientscreate DATABASE car_rental;
use car_rental;


CREATE TABLE `categories` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`category` VARCHAR(50) NOT NULL,
`daily_rate` DECIMAL(10,2) NOT NULL,
 `weekly_rate` DECIMAL(10,2) NOT NULL,
 `monthly_rate` DECIMAL(10,2) NOT NULL,
 `weekend_rate` DECIMAL(10,2) NOT NULL
); 


CREATE TABLE `cars` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`plate_number` VARCHAR(20) NOT NULL UNIQUE,
`make` VARCHAR(30) NOT NULL,
`model` VARCHAR(30) NOT NULL,
`car_year` YEAR NOT NULL,
`category_id` INT NOT NULL,
CONSTRAINT fk_cars_categories
FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
`doors` INT NOT NULL,
`pictures` BLOB,
`car_condition` TEXT NOT NULL,
`available` BOOLEAN
); 

CREATE TABLE `employees` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL,
`title` VARCHAR(50) NOT NULL,
`notes` TEXT
); 

CREATE TABLE `customers` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`driver_licence_number` VARCHAR(50) NOT NULL,
`full_name` VARCHAR(80) NOT NULL,
`address` VARCHAR(250) NOT NULL,
`city` VARCHAR(20) NOT NULL,
`zip_code` INT NOT NULL,
`notes` TEXT
); 


CREATE TABLE `rental_orders` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`employee_id` INT,
CONSTRAINT fk_rental_orders_employees
FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),

`customer_id` INT,
CONSTRAINT fk_rental_orders_customers
FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),

`car_id` INT,
CONSTRAINT fk_rental_orders_cars
FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),

`car_condition` TEXT NOT NULL,
`tank_level` FLOAT NOT NULL,
`kilometrage_start` FLOAT NOT NULL,
`kilometrage_end` FLOAT NOT NULL,
`total_kilometrage` FLOAT NOT NULL,
`start_date` DATE NOT NULL,
`end_date` DATE NOT NULL,
`total_days` INT NOT NULL,
`rate_applied` VARCHAR(50) NOT NULL,
`tax_rate` DECIMAL(10,2) NOT NULL,
`order_status` VARCHAR(10) NOT NULL,
`notes` TEXT
); 

INSERT INTO `categories`
VALUES
 (1,'c1', 1.1, 2.1, 3.1, 4.1),
 (2,'c2', 1.2, 2.2, 3.2, 4.2),
 (3,'c3', 1.3, 2.3, 3.3, 4.3);
 
 
 INSERT INTO `cars`
VALUES
 (1,'pn1', 'make1', 'model1', '2001', 1, 1, null, 'condition1', true),
 (2,'pn2', 'make2', 'model2', '2002', 2, 2, null, 'condition2', true),
 (3,'pn3', 'make3', 'model3', '2003', 3, 3, null, 'condition3', false);
 
 INSERT INTO `employees`
VALUES
(1,'f1','l1','t1', 'note1'),
(2,'f2','l2','t2', 'note2'),
(3,'f3','l3','t3', 'note3');

 INSERT INTO `customers`
VALUES
(1, '11111111', 'name1', 'address1', 'city1', 1, 'note1'),
(2, '22222222', 'name2', 'address2', 'city2', 2, 'note2'),
(3, '33333333', 'name3', 'address3', 'city3', 3, 'note3');

 INSERT INTO `rental_orders`
VALUES
(1, 1, 1, 1, 'condition1', 1.1, 101.1, 101.11, 1.1, '2020-01-11','2020-01-12',1, 'y', 1.1, 'status1', 'note1'),
(2, 2, 2, 2, 'condition2', 2.1, 201.1, 201.11, 2.2, '2020-02-22','2020-02-23',3, 'y', 2.2, 'status2', 'note2'),
(3, 3, 3, 3, 'condition3', 3.1, 301.1, 301.11, 3.3, '2020-03-11','2020-03-12',3, 'y', 3.3, 'status3', 'note3');
