CREATE database `soft_uni`;
USE `soft_uni`;

CREATE TABLE `towns`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` varchar(45) NOT NULL
);

create table `addresses`(
`id` INT PRImary key auto_increment,
`address_text` varchar(100) NOT NULL,
`town_id` INT,
CONSTRAINT fk_addresses_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`)
);

CREATE TABLE `departments`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` varchar(45) NOT NULL
);

CREATE TABLE `employees`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` varchar(45) NOT NULL,
`middle_name` varchar(45) NOT NULL,
`last_name` varchar(45) NOT NULL,
`job_title` varchar(45) NOT NULL,
`department_id` INT,
CONSTRAINT fk_employees_departments
FOREIGN KEY (`department_id`)
REFERENCES `departments`(`id`),
`hire_date` DATE NOT NULL,
`salary` DECIMAL NOT NULL,
`address_id` INT,
CONSTRAINT fk_employees_addresses
FOREIGN KEY (`address_id`)
REFERENCES `addresses`(`id`)
);


