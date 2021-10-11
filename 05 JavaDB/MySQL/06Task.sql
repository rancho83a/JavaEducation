DROP table `people`;
CREATe TABLE `people`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` varchar(200) not null,
`picture` BLOB(2048),
`height` double(10,2),
`weight` double(10,2),
`gender` CHAR(1) not null,
`birthday` date not null,
`biography` text
);

INSERT INTO `people` (`name`,`picture`,`height`,`weight`,`gender`,`birthday`,`biography`)
values
('name1',null, 1.11, 2.22, 'm', '2012-02-02','qwertyui'),
('name2',null, null, 2.22, 'm', '2011-02-02',null),
('name3',null, 1.11, null, 'f', '2010-02-02','wdfdgjkhl;hgfghjlk;ljkhgfddhgjqwertyui'),
('name4',null, null, null, 'm', '2012-02-02',null),
('name5',null, 1, 2, 'm', '2012-02-02','qwe09876543345678908765432456789876543245678rtyui');
