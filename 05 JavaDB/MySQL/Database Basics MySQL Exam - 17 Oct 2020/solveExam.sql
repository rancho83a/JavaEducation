#2
INSERT INTO products_stores(product_id,store_id)
		SELECT
        id as product_id, 1
        FROM products
		WHERE id not in (SELECT  product_id FROM products_stores as ps);

#3

UPDATE employees as e 
JOIN stores as s
ON e.store_id=s.id

SET manager_id = 3, salary=salary-500 

WHERE YEAR(hire_date)>2003 and s.name not in ('Cardguard','Veribet');

#4

DELETE
FROM employees
WHERE manager_id is not null and salary>=6000;

#5
SELECT first_name, middle_name, last_name, salary, hire_date
from employees
ORDER by hire_date DESC;

#6
SELECT pr.name as product_name,  price, pr.best_before, CONCAT(LEFT(description,10), '...') as short_description,  pi.url
FROM products as pr
JOIN pictures as pi
ON pr.picture_id=pi.id
WHERE char_length(`description`)>100 AND YEAR(pi.added_on) <2019 and price>20
ORDER by price DESC;

#7

SELECT s.`name`, COUNT(product_id)  as product_count, ROUND(AVG(p.price),2) as 	`avg`
FROM stores as s
LEFT JOIN products_stores as ps
ON s.id=ps.store_id
LEFT JOIN products as p
ON p.id=ps.product_id
GROUP BY s.id
ORDER BY product_count DESC, `avg` DESC, s.id ;

#8
SELECT  
	CONCAT(first_name, ' ', last_name) as full_name,
    s.name as Store_name,
    a.name,
    salary
from employees as e
JOIN stores as s
ON e.store_id = s.id
JOIN addresses as a
ON a.id=s.address_id
WHERE salary<4000 AND a.name LIKE '%5%' AND char_length(s.name)>8 AND right(e.last_name,1)='n';

#9
SELECT reverse(s.name), CONCAT(UPPER(t.name),'-',a.name )as full_address, COUNT(e.id) as employees_count
FROM stores as s
JOIN addresses as a
ON a.id=s.address_id
JOIN towns as t
ON t.id=a.town_id
JOIN employees as e
ON s.id=e.store_id
GROUP by s.name
HAVING employees_count>=1
ORDER BY full_address;

#10
DELIMITER $$
CREATE FUNCTION `udf_top_paid_employee_by_store`(store_name VARCHAR(50))
RETURNS VARCHAR(250)
DETERMINISTIC
BEGIN

RETURN (
SELECT CONCAT(first_name,' ', middle_name,'. ',last_name ,' works in store for ', 2020-YEAR(hire_date),' years')
from stores as s
JOIN employees as e
ON e.store_id=s.id
WHERE s.name = store_name
ORDER BY salary DESC
LIMIT 1
) ;
END;$$
DELIMITER ;

SELECT udf_top_paid_employee_by_store('Stronghold') as 'full_info';


SELECT CONCAT(first_name,' ', middle_name,'. ',last_name ,' works in store for ', 2020-YEAR(hire_date), ' years of experience',' years')
from stores as s
JOIN employees as e
ON e.store_id=s.id
WHERE s.name = 'Stronghold'
ORDER BY salary DESC
LIMIT 1;


#11
SELECT p.price 
FROM products as p
JOIN products_stores as ps
ON p.id=ps.product_id
JOIN stores as s
ON ps.store_id=s.id
JOIN addresses as a
ON a.id=s.address_id
WHERE a.name = '07 Armistice Parkway';


UPDATE products as p
JOIN products_stores as ps
ON p.id=ps.product_id
JOIN stores as s
ON ps.store_id=s.id
JOIN addresses as a
ON a.id=s.address_id
SET salary = 
CASE
WHEN RIGHT('07 Armistice Parkway')='0' then salary+100
ELSE salary+200
END

WHERE a.name = '07 Armistice Parkway';