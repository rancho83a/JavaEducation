SELECT * FROM employees as e
JOIN departments as d
ON e.department_id = d.department_id;
#USING department_id;
#LIMIT 11111;

#1

SELECT employee_id, CONCAT(e.first_name, ' ', e.last_name) as full_name,   d.department_id, d.name
#d.name as department_name
FROM employees as e
JOIN departments as d
ON e.employee_id=d.manager_id
ORDER BY employee_id
limit 5;

#2
SELECT 
    t.town_id, t.`name` AS 'town_name', a.address_text
FROM
    addresses AS a
        LEFT JOIN
    towns AS t ON a.town_id = t.town_id
WHERE
    t.`name` IN ('Sofia' , 'San Francisco', 'Carnation')
ORDER BY t.town_id , a.address_id;


#3
SELECT employee_id, first_name, last_name, department_id, salary from employees
WHERE manager_id is null;


#4
SELECT COUNT(*)  as count 
FROM employees
WHERE salary > (
SELECT AVG(salary) from employees
);