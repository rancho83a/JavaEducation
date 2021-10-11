create TABLE `users` (
`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(26) NOT NULL,
`profile_picture` BLOB,
`last_login_time` TIMESTAMP,
`is_deleted` BOOLEAN NOT NULL

);

INSERT INTO `users`
VALUES
(1,'u1','p1', NULL, '2008-01-01 00:00:01', false),
(2,'u2','p2', NULL, '2008-01-01 00:00:01', false),
(3,'u3','p3', NULL, '1970-01-04 00:00:01', true),
(4,'u4','p5', NULL, NULL, false),
(5,'u5','p5', NULL, '1975-01-01 00:00:01', false);
