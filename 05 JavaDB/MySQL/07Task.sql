DROP table `users`;
create table `users`(
`id` BIGINT PRIMARY key auto_increment,
`username` varchar(30) unique not null,
`password` varchar(26) not null,
`profile_picture` blob(900),
`last_login_time` time not null,
`is_deleted` boolean
);
INSERT into `users`(`username`,`password`,`profile_picture`,`last_login_time`,`is_deleted`)
values
('user1','pas1',null,'08:00:00', true),
('user2','pas2',null,'02:00:00', false),
('user3','pas3',null,'04:00:00', true),
('user4','pas4',null,'03:00:00', true),
('user5','pas5',null,'05:00:00', true);


