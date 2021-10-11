ALTER TABLE `users`
CHANGE COLUMN `last_login_time`  `last_login_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

INSERT INTO `users`(`id`, `username`, `password`, `profile_picture`, `is_deleted`)
VALUES 
(6,'u11','p1', NULL,  false);
