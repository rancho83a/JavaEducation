#1
SELECT first_name, last_name
FROM employees
 WHERE first_name LIKE 'Sa%'
 ORDER BY employee_id;
 
 #1
 SELECT first_name, last_name FROM employees
 WHERE substr(first_name, 1,2) = 'Sa'
 ORDER BY employee_id;
 
 #2
 SELECT first_name, last_name FROM employees
 WHERE lower(last_name) LIKE '%ei%'
 ORDER BY employee_id;
 
 
 #3
 SELECT first_name from employees
 WHERE department_id IN(3,10) AND (year(hire_date) BETWEEN 1995 AND 2005)
 ORDER BY employee_id;
 
  #4
 SELECT first_name, last_name from employees
 WHERE LOWER(job_title) NOT LIKE '%engineer%';
 
 
 #5
 SELECT `name` from towns
 WHERE char_length(`name`) IN(5,6)
 ORDER BY `name`;
 
 #6
 SELECT town_id,`name` from towns
 WHERE  LEFT(upper(`name`),1) in('K','M','B','E')
 ORDER BY `name`;
 
 #7
 SELECT town_id,`name` from towns
 WHERE  LEFT(upper(`name`),1) not in('R','B','D')
 ORDER BY `name`;
 
 #8
 CREATE VIEW  `v_employees_hired_after_2000` AS
 SELECT first_name, last_name from employees
 WHERE YEAR(hire_date)>2000;
 SELECT * from v_employees_hired_after_2000;
 
 #9
 SELECT first_name, last_name from employees
 WHERE char_length(last_name) = 5;
 
 #10
 SELECT country_name, iso_code from countries
 WHERE country_name LIKE '%a%a%a%'
 ORDER BY iso_code ;
 
#11
SELECT peak_name, river_name, Lower(concat(peak_name, SUBSTR( river_name,2))) AS mix  
from peaks, rivers
Where right(peak_name,1) = LEFT(river_name,1) 
ORDER BY mix;

#12 
  SELECT  `name`, DATE_FORMAT(`start`, '%Y-%m-%d') AS `start`
FROM
    games
WHERE
    YEAR(`start`) BETWEEN 2011 AND 2012
ORDER BY `start` , `name`
LIMIT 50;

SELECT `name`, date_format(`start`, '%Y-%m-%d') as `start` FROM games
WHERE year(`start`) BETWEEN 2011 AND 2012
ORDER BY `start`,`name`
LIMIT 50;

#13
SELECT user_name, SUBSTR(email, LOCATE('@',email)+1) as 'Email Provider' 
FROM users
ORDER BY `Email Provider`, user_name;

#14
SELECT user_name, ip_address FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY user_name;

#15
SELECT 
    `name`,
    (CASE
        WHEN HOUR(`start`) < 12 THEN 'Morning'
        WHEN HOUR(`start`) < 18 THEN 'Afternoon'
        ELSE 'Evening'
    END) AS 'Part of the Day',
    (CASE
        WHEN `duration` <= 3 THEN 'Extra Short'
        WHEN `duration` <= 6 THEN 'Short'
        WHEN `duration` <= 10 THEN 'Long'
        ELSE 'Extra Long'
    END) AS 'Duration'
FROM
    games;
    
    #16
   SELECT 
    product_name,
    order_date,
    (DATE_ADD(order_date, INTERVAL 3 DAY)) AS 'pay_due',
    (DATE_ADD(order_date, INTERVAL 1 MONTH)) AS 'deliver_due'
FROM
    orders;