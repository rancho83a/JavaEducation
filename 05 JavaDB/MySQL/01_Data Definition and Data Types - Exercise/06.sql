/*
id – unique number for every person there will be no more than 2 31 -1people. (Auto incremented)
 name – full name of the person will be no more than 200 Unicode characters. (Not null)
 picture – image with size up to 2 MB. (Allow nulls)
 height – In meters. Real number precise up to 2 digits after floating point. (Allow nulls)
 weight – In kilograms. Real number precise up to 2 digits after floating point. (Allow nulls)
 gender – Possible states are m or f. (Not null)
 birthdate – (Not null)
 biography – detailed biography of the person it can contain max allowed Unicode characters. (Allow nulls)
*/

CREATE TABLE `people` (
`id`  INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` BLOB,
`height` FLOAT(5,2),
`weight` FLOAT(5,2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT
);

INSERT INTO `people`
VALUES
(1, '1s', NULL, 1.22, 0.2, 'm', '1951-01-01', '1sdfsdf'),
(2, '2s', NULL, 2.22, 0.2, 'f', '1950-01-21', '2sdfsdf'),
(3, '3s', NULL, 3.22, 0.2, 'm', '1952-01-01', '3sdfsdf'),
(4, '4s', NULL, 4.22, 0.2, 'm', '1953-01-01', '4sdfsdf'),
(5, '5s', NULL, NULL, NULL, 'f', '1954-01-01', '5sdfsdf');