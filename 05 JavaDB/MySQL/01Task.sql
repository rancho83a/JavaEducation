CREATE database `minions`; 
USE `minions`;

create table `minions`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`age` INT
);

create table `town`(
`town_id` INT primary KEY auto_increment,
`name` varchar(45) NOT null
);

insert into `minions`(`name`,`age`)
VALUES
('Ivan', 20),
('Pesho', null);