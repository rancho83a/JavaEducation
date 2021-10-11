#2
INSERT INTO addresses (address, town, country, user_id)
(
SELECT username, `password`,ip,age 
FROM users
Where gender ='M'
);

#3

UPDATE addresses

SET country = (
CASE LEFT(country,1)
WHEN   'B' THEN 'Blocked'
WHEN  'T' THEN 'Test'
WHEN 'P' THEN 'In Progress'

END
)
WHERE  left(country,1)  in ('B','P','T')
;

#4
DELETE FROM addresses
WHERE (id mod 3) =0;
# WHERE (id % 3) =0;

#5
SELECT username, gender, age
FROM users
ORDER BY age DESC, username;


#6
SELECT p.id, p.date, p.description, COUNT(c.id) as commentsCount
FROM photos as p
JOIN comments as c
ON p.id=c.photo_id
GROUP BY p.id
ORDER BY commentsCount DESC, p.id
LIMIT 5;

#7
SELECT CONCAT(up.user_id,' ', u.username) as id_username, u.email
FROM users_photos as up
JOIN users as u
ON up.user_id=u.id
WHERE up.user_id=up.photo_id
ORDER BY up.user_id;


#8
SELECT p.id as photo_id, COUNT(DISTINCT l.id) as likes_count, COUNT( DISTINCT c.id) as comments_count
FROM photos as p
LEFT JOIN likes as l
ON p.id = l.photo_id
LEFT JOIN comments as c
ON p.id=c.photo_id
GROUP BY p.id
ORDER BY likes_count DESC, comments_count DESC, p.id;

SELECT p.id as photo_id, 
(
SELECT COUNT(*) FROM likes as l WHERE l.photo_id=p.id
) as likes_count,
(
SELECT COUNT(*) FROM comments as c WHERE c.photo_id=p.id
) as comments_count

FROM photos as p
ORDER BY likes_count DESC, comments_count DESC, p.id;



#9

SELECT concat(SUBSTR(description,1,30),'...') as summary, date
FROM photos as p
WHERE DAY(p.date) =10
ORDER BY p.date DESC;

#10
DELIMITER $$
CREATE FUNCTION `udf_users_photos_count`(username VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN

RETURN(
SELECT COUNT(*)
FROM users as u
LEFT JOIN users_photos as up
ON u.id=up.user_id
WHERE u.username = username) ;
END; $$
DELIMITER ;
SELECT udf_users_photos_count('ssantryd') AS photosCount;


SELECT COUNT(*)
FROM users as u
JOIN users_photos as up
ON u.id=up.user_id
WHERE u.username = 'ygeratt0';


#11

DELIMITER $$
CREATE PROCEDURE `udp_modify_user`(address VARCHAR(30), town VARCHAR(30))
BEGIN
UPDATE users as u
RIGHT JOIN addresses as a
ON u.id=a.user_id
SET age=age+10
WHERE  a.id is not null AND a.address = address AND a.town=town;
END; $$
DELIMITER ;

CALL `udp_modify_user`('97 Valley Edge Parkway','DivinГіpolis');
SELECT u.username, u.email,u.gender,u.age,u.job_title FROM users AS u
WHERE u.username = 'eblagden21';

UPDATE users as u
JOIN addresses as a
ON u.id=a.user_id
SET age=age+10
WHERE address = '97 Valley Edge Parkway' AND town='DivinГіpolis'
;