CREATE VIEW `test`AS (
SELECT *, id AS NumberAD
FROM employees
WHERE id IN (2,3,4)
);

SELECT * FROM test;

CREATE TABLE `new_table`
AS SELECT last_name, job_title, salary
FROM employees;

CREATE TABLE fired (

SELECT first_name, id AS old_empl_number, NOW() AS fire_date
FROM employees
);