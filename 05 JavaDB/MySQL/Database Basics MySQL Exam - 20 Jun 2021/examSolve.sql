#2 insert
INSERT INTO clients(full_name, phone_number)
SELECT 
CONCAT(first_name,' ', last_name), 
CONCAT ('(088) 9999', d.id*2)

FROM drivers  as d
WHERE d.id BETWEEN 10 and 20;



#3 update
UPDATE cars 
SET `condition` = 'C'
WHERE  (mileage>=800000  OR mileage is NULL) AND `year`<=2010 and make not LIKe '%Mercedes-Benz%';

SELECT *
FROM cars
WHERE  (mileage>=800000  OR mileage is NULL) AND `year`<=2010 and make not LIKe '%Mercedes-Benz%';


#4

DELETE
FROM clients as c
WHERE c.id not in (SELECT client_id FROM courses) and char_length( c.full_name) >3;



#5

SELECT make, model, `condition`
FROM cars
ORDER BY id;

#6
SELECT first_name, last_name, c.make, c.model, c.mileage 
FROM drivers as d
JOIN cars_drivers as cd
ON d.id=cd.driver_id
JOIN cars as c
ON c.id=cd.car_id
WHERE c.mileage is not NULL
ORDER BY c.mileage DESC, first_name;


#7
SELECT c.id, c.make, c.mileage, COUNT(co.id) as count_of_courses, ROUND(AVG(co.bill),2) as avg_bill
FROM cars as c
LEFT JOIN courses as co
ON c.id=co.car_id
GROUP BY c.id
HAVING count_of_courses!=2
ORDER BY count_of_courses DESC, c.id;

#8
SELECT cl.full_name, COUNT(ca.id) as count_of_cars, SUM(co.bill) as total_sum
FROM clients as cl
JOIN courses as co
ON cl.id=co.client_id
JOIN cars as ca
ON ca.id=co.car_id
WHERE cl.full_name LIKE '_a%'
GROUP by cl.id
HAVING count_of_cars>1
# and cl.full_name LIKE '_a%'
ORDER BY full_name;

#9
SELECT a.`name`,
( CASE
 WHEN  HOUR(co.`start`) BETWEEN 6 and 20 THEN 'Day'
 ELSE 'Night'
 END) as day_time,
  bill,
cl.full_name,
ca.make,
ca.model,
cat.`name` as category_name
 
FROM courses as co
JOIN addresses as a
ON a.id = co.from_address_id
JOIN clients as cl
ON cl.id=co.client_id
JOIN cars as ca
ON ca.id=co.car_id
JOIN categories as cat
ON ca.category_id=cat.id
ORDER BY co.id;

#10
DELIMITER $$
CREATE FUNCTION `udf_courses_by_client`(phone_num VARCHAR (20))
RETURNS INTEGER
DETERMINISTIC
BEGIN

RETURN (
SELECT COUNT(co.id)
 from clients as cl
 JOIN courses as co
 ON co.client_id = cl.id
 WHERE cl.phone_number = phone_num
 GROUP BY cl.id

);
END; $$
DELIMITER ;
SELECT udf_courses_by_client ('(803) 6386812') as `count`;
SELECT udf_courses_by_client ('(831) 1391236') as `count`;

SELECT COUNT(co.id)
 from clients as cl
 JOIN courses as co
 ON co.client_id = cl.id
 WHERE cl.phone_number = '(803) 6386812'
 GROUP BY cl.id;
 
 
 
 #11
 
 DELIMITER $$
 CREATE PROCEDURE `udp_courses_by_address`(address_name VARCHAR(100))
BEGIN
SELECT a.`name`, cl.full_name as full_names, 
 
 (
 CASE 
 WHEN co.bill <=20 THEN 'Low'
 WHEN co.bill<=30 THEN 'Medium'
 ELSE 'High'
 END
 )
 as level_of_bill,
 c.make, c.`condition`, cat.`name` as cat_name 
 FROM addresses as a
 JOIN courses as co
 ON co.from_address_id = a.id
 JOIN clients as cl
 ON cl.id=co.client_id
 JOIN cars as c
 ON c.id=co.car_id
 JOIN categories as cat
 ON c.category_id=cat.id
 WHERE a.`name` = address_name
 
 ORDER BY c.make, cl.full_name;
END; $$
DELIMITER ;

CALL udp_courses_by_address('700 Monterey Avenue');

 
 SELECT a.`name`, cl.full_name as full_names, 
 
 (
 CASE 
 WHEN co.bill <=20 THEN 'Low'
 WHEN co.bill<=30 THEN 'Medium'
 ELSE 'High'
 END
 )
 as level_of_bill,
 c.make, c.`condition`, cat.`name` as cat_name 
 FROM addresses as a
 JOIN courses as co
 ON co.from_address_id = a.id
 JOIN clients as cl
 ON cl.id=co.client_id
 JOIN cars as c
 ON c.id=co.car_id
 JOIN categories as cat
 ON c.category_id=cat.id
 WHERE a.`name` = '700 Monterey Avenue'
 
 ORDER BY c.make, cl.full_name;
 
 
 SELECT EXTRACT(DAY FROM('2000-02-03'));
 
 SELECT DAY('2000-02-03');
 
 SELECT SUBSTR('SoftUni', 1,4);
 