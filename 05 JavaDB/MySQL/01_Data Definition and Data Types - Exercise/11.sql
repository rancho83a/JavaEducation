

CREATE TABLE `directors` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`director_name` VARCHAR(50) NOT NULL,
`notes` TEXT
); 

CREATE TABLE `genres` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`genre_name` VARCHAR(50) NOT NULL,
`notes` TEXT
); 

CREATE TABLE `categories` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`category_name` VARCHAR(50) NOT NULL,
`notes` TEXT
); 


CREATE TABLE `movies` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(50) NOT NULL,
`director_id` INT NOT NULL,
CONSTRAINT fk_movies_directors
FOREIGN KEY (`director_id`) REFERENCES `directors` (`id`),

`copyright_year` YEAR NOT NULL,
`length` TIMESTAMP NOT NULL,

`genre_id` INT NOT NULL,
CONSTRAINT fk_movies_genre
FOREIGN KEY (`genre_id`)
REFERENCES `genres` (`id`),

`category_id` INT NOT NULL,
CONSTRAINT fk_movies_category
FOREIGN KEY (`category_id`)
REFERENCES `categories` (`id`),

`rating` INT,
`notes` TEXT
); 

INSERT INTO `directors`
 VALUES
 (1,'d1','n1'),
 (2,'d2','n2'),
 (3,'d3','n3'),
 (4,'d4','n4'),
 (5,'d5','n5');
 
 INSERT INTO `genres`
 VALUES
 (1,'g1','n1'),
 (2,'g2','n2'),
 (3,'g3','n3'),
 (4,'g4','n4'),
 (5,'g5','n5');
 
  INSERT INTO `categories`
 VALUES
 (1,'c1','n1'),
 (2,'c2','n2'),
 (3,'c3','n3'),
 (4,'c4','n4'),
 (5,'c5','n5');
 
  INSERT INTO `movies`
 VALUES
 (1,'t1',1, 2001,'2000-01-01 01:01:01', 1, 1, 1, 'n1'),
 (2,'t2',2, 2002,'2000-01-01 02:01:01', 2, 2, 2, 'n2'),
 (3,'t3',1, 2003,'2000-01-01 03:01:01', 3, 3, 3, 'n3'),
 (4,'t4',4, 2004,'2000-01-01 04:01:01', 4, 4, 4, 'n4'),
 (5,'t5',5, 2005,'2000-01-01 05:01:01', 5, 5, 5, 'n5');

 
