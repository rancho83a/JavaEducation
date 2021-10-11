SELECT id, first_name, last_name, job_title FROM employees
ORDER BY id;

SELECT *, id, CONCAT('St1', ' ', first_name) AS concatString FROM employees;
SELECT *, id, concat_ws(' ', first_name, last_name, salary) AS full_name FROM employees;

