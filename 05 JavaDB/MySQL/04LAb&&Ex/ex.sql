#1
SELECT Count(*)  AS count from wizzard_deposits;

#2
SELECT MAX(magic_wand_size)AS 'longest_magic_wand' from wizzard_deposits;

#3
SELECT 
    deposit_group, MAX(magic_wand_size) AS 'longest_magic_wand'
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY `longest_magic_wand` , deposit_group;

#4
SELECT 
    deposit_group
FROM
    wizzard_deposits
GROUP BY deposit_group
HAVING AVG(magic_wand_size) = (SELECT 
        AVG(magic_wand_size) AS 'avg'
    FROM
        wizzard_deposits
    GROUP BY deposit_group
    ORDER BY `avg`
    LIMIT 1);
    
    SELECT 
    deposit_group
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY AVG(`magic_wand_size`) 
    LIMIT 1;
    
    #5
SELECT deposit_group, SUM(deposit_amount) AS `total_sum`
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY `total_sum`;

#6
SELECT deposit_group, SUM(deposit_amount)
FROM wizzard_deposits
WHERE `magic_wand_creator`='Ollivander family'
GROUP BY deposit_group
ORDER BY deposit_group;

#7
SELECT deposit_group, SUM(deposit_amount) as `total_sum`
FROM wizzard_deposits
WHERE `magic_wand_creator`='Ollivander family'
GROUP BY deposit_group
HAVING `total_sum`<150000
ORDER BY `total_sum` DESC;

#8
SELECT 
    deposit_group,
    magic_wand_creator,
    MIN(deposit_charge) AS `min_deposit_charge`
FROM
    wizzard_deposits
GROUP BY deposit_group , magic_wand_creator
ORDER BY magic_wand_creator , deposit_group;

#9
SELECT 
    `age_group`, COUNT(*) AS `wizard_count`
FROM
    (SELECT 
        CASE
                WHEN age <= 10 THEN '[0-10]'
                WHEN age BETWEEN 11 AND 20 THEN '[11-20]'
                WHEN age BETWEEN 21 AND 30 THEN '[21-30]'
                WHEN age BETWEEN 31 AND 40 THEN '[31-40]'
                WHEN age BETWEEN 41 AND 50 THEN '[41-50]'
                WHEN age BETWEEN 51 AND 60 THEN '[51-60]'
                WHEN age >= 61 THEN '[61+]'
            END AS `age_group`
    FROM
        wizzard_deposits) AS `ageGroup`
GROUP BY `ageGroup`.`age_group`
ORDER BY  `age_group`;

#9
SELECT 
    (CASE
        WHEN age <= 10 THEN '[0-10]'
        WHEN age BETWEEN 11 AND 20 THEN '[11-20]'
        WHEN age BETWEEN 21 AND 30 THEN '[21-30]'
        WHEN age BETWEEN 31 AND 40 THEN '[31-40]'
        WHEN age BETWEEN 41 AND 50 THEN '[41-50]'
        WHEN age BETWEEN 51 AND 60 THEN '[51-60]'
        WHEN age >= 61 THEN '[61+]'
    END) AS `age_group`, COUNT(*) AS `wizard_count`
FROM
    wizzard_deposits
    GROUP by `age_group`
    ORDER BY `wizard_count`;

#10
SELECT LEFT(first_name,1) AS `letter` 
FROM wizzard_deposits
WHERE deposit_group='Troll Chest'
GROUP BY letter
ORDER BY letter;

#11
SELECT 
    deposit_group,
    is_deposit_expired,
    AVG(deposit_interest) AS `average_interest`
FROM
    wizzard_deposits
WHERE
    `deposit_start_date` > '1985-01-01'
GROUP BY deposit_group , is_deposit_expired
ORDER BY deposit_group DESC , is_deposit_expired;

#12
SELECT department_id, MIN(salary) AS `minimum_salary`
FROM employees
WHERE hire_date>'2000-01-01 00:00:00.000000'
GROUP by department_id
HAVING department_id IN (2,5,7)
ORDER BY department_id;

#13
CREATE TABLE `avg_salary` 
SELECT * FROM employees
WHERE salary>30000;

DELETE FROM `avg_salary`
WHERE manager_id=42;

UPDATE avg_salary
SET salary = salary + 5000
WHERE department_id=1;

SELECT department_id, AVG(salary) AS `avg_salary`
FROM avg_salary
GROUP BY department_id
ORDER BY department_id;


#14
SELECT department_id, MAX(salary) as `max_salary`
FROM employees
GROUP BY department_id
HAVING `max_salary` <30000 OR `max_salary`>70000
ORDER BY department_id;

#15
SELECT COUNT(*) AS '' FROM employees
WHERE manager_id is NULL;

#16
SELECT department_id, (

SELECT DISTINCT e2.salary
FROM employees AS e2
WHERE e2.department_id=e.department_id
ORDER BY e2.salary DESC
LIMIT 1 OFFSET 2

) AS `third_highest_salary`  

FROM employees AS e
GROUP BY department_id
HAVING `third_highest_salary` is not null
ORDER BY department_id;

#17

SELECT e.first_name, e.last_name, e.department_id
FROM employees As e
WHERE e.salary > (
	SELECT AVG(e2.salary)
	FROM employees AS e2
	WHERE e2.department_id=e.department_id
) 
ORDER BY e.department_id, e.employee_id
LIMIT 10;

#18

SELECT department_id, SUM(salary) AS `total_salary`
FROM employees
GROUP BY department_id
ORDER BY department_id;

