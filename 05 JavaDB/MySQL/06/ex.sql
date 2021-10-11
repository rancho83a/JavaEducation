
#1
SELECT employee_id, job_title, address_id, a.address_text
FROM employees
JOIN addresses as a
USING (address_id)
ORDER BY address_id
LIMIT 5;

#2
SELECT first_name,last_name,  t.name,a.address_text
FROM employees
JOIN addresses as a
USING (address_id)
JOIN towns as t
ON a.town_id=t.town_id
ORDER BY first_name, last_name
LIMIT 5;


#3
SELECT employee_id, first_name,last_name, d.name
FROM employees
JOIN departments as d
USING (department_id)
WHERE d.name='Sales'
ORDER BY employee_id DESC
LIMIT 5;


#4
SELECT employee_id, first_name, salary, d.name as department_name
FROM employees as e
JOIN departments as d
USING (department_id)
WHERE salary>15000
ORDER BY d.department_id DESC
LIMIT 5;

#5
SELECT e.employee_id, first_name
FROM employees as e
LEFT JOIN employees_projects as t
on t.employee_id = e.employee_id
Where t.employee_id is null
ORDER BY e.employee_id DESC
LIMIT 3;


SELECT e.employee_id, first_name
FROM employees as e
WHERE employee_id NOT IN (
SELECT employee_id FROM employees_projects as t
)
ORDER BY e.employee_id DESC
LIMIT 3;


#6
SELECT first_name, last_name, hire_date, d.name as dept_name
FROM employees as e
JOIN departments as d
USING (department_id)
WHERE (d.name='Sales'OR  d.name='Finance') AND hire_date>'1991-01-01 00:00:00.000000'
ORDER BY hire_date;

#7
SELECT 
    e.employee_id, first_name, p.name AS project_name
FROM
    employees AS e
        JOIN
    employees_projects AS ep ON ep.employee_id = e.employee_id
        JOIN
    projects AS p 
    
    ON p.project_id = ep.project_id
    #USING (project_id)
WHERE
    DATE (p.start_date) > '2002-08-13'
        AND p.end_date IS NULL
ORDER BY e.first_name , p.`name`
LIMIT 5;


#8
SELECT e.employee_id, e.first_name,  
IF (year(p.start_date)>=2005,  null, p.name) as 'project_name'
FROM employees as e
JOIN employees_projects as ep
USING(employee_id)
JOIN projects as p
using(project_id)
WHERE employee_id=24
ORDER BY `project_name`;


#9
SELECT e1.employee_id, e1.first_name, e1.manager_id, e2.first_name as 'manager_name'
FROM employees as e1
JOIN employees as e2
ON e1.manager_id = e2.employee_id

WHERE e1.manager_id in (3, 7)
ORDER BY first_name;

#10
SELECT 
    e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS 'employee_name',
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name,
    d.name AS department_name
FROM
    employees AS e
        JOIN
    employees AS m ON e.manager_id = m.employee_id
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE
    e.manager_id IS NOT NULL
ORDER BY e.employee_id
LIMIT 5;


#11
SELECT AVG(salary) as min_average_salary FROM employees
GROUP BY department_id
ORDER BY min_average_salary
LIMIT 1;

#12
SELECT c.country_code,  m.mountain_range, p.peak_name, p.elevation
From countries as c
Join mountains_countries  as mc
using (country_code)
join mountains as m
on mc.mountain_id=m.id
join peaks as p
on m.id=p.mountain_id
WHERE c.country_code='BG' and p.elevation>2835
ORDER BY p.elevation DESC;

#13
select country_code, Count(*) as mountain_range
FROM mountains_countries
GROUP BY country_code
HAVING country_code in ('BG','RU','US')
ORDER BY mountain_range DESC;

#14
SELECT c.country_name, r.river_name
From countries as c
left join countries_rivers as cr
using(country_code)
left join rivers as r
on cr.river_id=r.id

WHERE c.continent_code='AF'
ORDER BY country_name
LIMIT 5;


#15

SELECT (COUNT(country_code)) as count
FROM countries
Where continent_code='AF'
GROUP BY currency_code
ORDER by count DESC
LIMIT 1
;


SELECT continent_code, currency_code,(COUNT(*)) as currency_usage
FROM  countries AS c
GROUP BY continent_code, currency_code
HAVING currency_usage = 
(SELECT (COUNT(country_code)) as count
FROM countries as c2
Where c.continent_code=c2.continent_code
GROUP BY currency_code
ORDER by count DESC
LIMIT 1) AND currency_usage >1
 
ORDER BY continent_code, currency_code;




#16a

SELECT 
    COUNT(country_name) AS country_count
FROM
    countries AS c
        LEFT JOIN
    mountains_countries AS mc USING (country_code)
WHERE
    mountain_id IS NULL;

#16b

SELECT 
    COUNT(country_name) AS country_count
FROM
    countries AS c
WHERE
    country_code NOT IN (SELECT 
            country_code
        FROM
            mountains_countries);
            
            
#17   
         
SELECT 
    country_name,
    MAX(p.elevation) AS highest_peak_elevation,
    MAX(r.length) AS longest_river_length
FROM
    countries AS c
        JOIN
    mountains_countries AS mc USING (country_code)
        JOIN
    mountains AS m ON m.id = mc.mountain_id
        JOIN
    peaks AS p ON p.mountain_id = m.id
        JOIN
    countries_rivers AS cr USING (country_code)
        JOIN
    rivers AS r ON r.id = cr.river_id
GROUP BY country_name
ORDER BY highest_peak_elevation DESC , longest_river_length DESC , country_name
LIMIT 5;