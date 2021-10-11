#1
SELECT * FROM departments
ORDER BY department_id;

#2
SELECT name FROM departments
ORDER BY department_id;

#3
SELECT first_name, last_name, salary FROM employees
ORDER BY employee_id;

#4
SELECT first_name,middle_name, last_name FROM employees
ORDER BY employee_id;

#5
SELECT Concat(first_name,'.',last_name,'@softuni.bg') AS full_email_address FROM employees;

#6
SELECT DISTINCT salary FROM employees
ORDER BY employee_id;

#7
SELECT * FROM employees
WHERE job_title='Sales Representative'
ORDER BY employee_id;

#8
SELECT first_name, last_name, job_title AS JobTitle FROM employees
WHERE salary BETWEEN 20000 AND 30000
ORDER BY employee_id;

#9
SELECT concat_ws(' ', first_name, middle_name, last_name) AS 'Full Name' from employees
where salary IN (25000, 14000, 12500, 23600);

#10
SELECT first_name, last_name FROM employees
WHERE manager_id is NULL;

#11
SELECT first_name, last_name, salary from employees
WHERE salary>50000
ORDER BY salary DESC;

#12
SELECT first_name, last_name from employees
ORDER BY salary DESC
LIMIT 5;

#13
SELECT first_name, last_name from employees
WHERE department_id!=4;

#14
SELECT employee_id AS id, first_name as 'First Name', last_name as 'Last Name', middle_name as 'Middle Name', job_title,
department_id as 'DeptID', manager_id as 'MngrID', hire_date AS 'Hire Date', salary, address_id from employees
ORDER BY salary DESC, first_name, last_name DESC, middle_name;

#15
CREATE view v_employees_salaries AS
SELECT first_name, last_name, salary FROM employees; 
SELECT * from v_employees_salaries;

#16
CREATE VIEW v_employees_job_titles AS 
SELECT concat_ws(' ', first_name, middle_name, last_name) AS full_name, job_title from employees;
SELECT * FROM v_employees_job_titles;

#17
SELECT DISTINCT job_title FROM employees
ORDER BY job_title;


#18
SELECT * FROM projects
ORDER BY start_date, `name`, project_id
LIMIT 10;


#19
SELECT first_name, last_name, hire_date FROM employees
ORDER BY hire_date DESC
LIMIT 7;

#20
UPDATE employees 
SET salary = 1.12*salary
WHERE department_id IN (1,2,4,11);
SELECT salary from employees;

#21
SELECT peak_name FROM peaks
ORDER BY peak_name;

#22
SELECT country_name, population  FROM countries
WHERE continent_code = 'EU'
ORDER BY population DESC, country_name
LIMIT 30;

#23
SELECT country_name, country_code,
IF( currency_code = 'EUR', 'Euro', 'Not Euro') as currency
from countries
ORDER BY country_name;

#24
SELECT name FROM characters
ORDER BY name;