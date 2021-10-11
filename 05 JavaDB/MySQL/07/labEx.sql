
#1
DELIMITER ###
CREATE  FUNCTION `ufn_count_employees_by_town`(town_name VARCHAR(45)) RETURNS int
    DETERMINISTIC
BEGIN
DECLARE result INT;
SET result := (SELECT COUNT(*) as count
FROM employees
JOIN addresses
using(address_id)
JOIN towns as t
using (town_id)
Where t.name = town_name);

RETURN result;

END ###
DELIMITER ;

SELECT ufn_count_employees_by_town(null) as RES;


#2

UPDATE employees as e
join departments as d
using(department_id)
SET salary=1.05*salary
WHERE d.name = '';

CALL usp_raise_salaries('Sales');

#3
UPDATE employees as e
SET salary = salary*1.05
WHERE employee_id = 1;

CALL usp_raise_salary_by_id(1);
CALL usp_raise_salary_by_id_2(1111);


#4
CREATE TABLE deleted_employees(

employee_id INT PRIMARY KEY AUTO_INCREMENT,

first_name VARCHAR(20),

last_name VARCHAR(20),

middle_name VARCHAR(20),

job_title VARCHAR(50),

department_id INT,

salary DECIMAL(19,4)

);



CREATE  TRIGGER `tr_deleted_employees` AFTER DELETE ON `employees` FOR EACH ROW BEGIN
INSERT INTO deleted_employees (first_name,last_name,middle_name,job_title,department_id,salary)
VALUES ( OLD.first_name,OLD.last_name,OLD.middle_name,OLD.job_title, OLD.department_id, OLD.salary);
END;



DELETE FROM employees
WHERE employee_id =2;