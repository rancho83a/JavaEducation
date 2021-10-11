#2  Insert

INSERT INTO coaches (first_name,last_name, salary, coach_level)
(
SELECT first_name, last_name, salary*2, char_length(first_name)
FROM players
WHERE age>=45
);



#3
UPDATE coaches as c
JOIN players_coaches as pc
ON pc.coach_id = c.id
SET coach_level=coach_level+1
WHERE c.first_name LIKE 'A%';

#3
UPDATE coaches as c
SET coach_level=coach_level+1
WHERE c.first_name LIKE 'A%' AND id IN (SELECT coach_id FROM players_coaches);


#4 Deleted

DELETE FROM players
WHERE age>=45;


#5
SELECT first_name, age, salary 
FROM players
ORDER BY salary DESC;

#6
SELECT p.id, concat_ws(' ',first_name, last_name) as full_name, age, position, hire_date
FROM players as p
JOIN skills_data as sd
ON p.skills_data_id=sd.id
WHERE age<=23 AND position='A' AND hire_date IS NULL AND  sd.strength>50
ORDER BY p.salary, p.age;

#7
SELECT t.`name` as team_name, t.established, fan_base, count(p.id) as players_count
FROM teams as t
LEFT JOIN players as p
ON p.team_id= t.id
GROUP by t.id
ORDER BY players_count DESC, fan_base DESC;

#8

SELECT  MAX(sd.speed) as max_speed, tw.name as town_name
FROM towns as tw
LEFT JOIN stadiums as s
ON s.town_id = tw.id
LEFT JOIN teams as t
ON t.stadium_id = s.id
LEFT JOIN players as p
ON p.team_id=t.id
LEFT JOIN skills_data as sd
ON p.skills_data_id = sd.id

WHERE t.`name` !='Devify'
GROUP by tw.id
ORDER by max_speed DESC, town_name 
;


#9

SELECT c.`name`, count(p.id) as total_count_of_players, SUM(p.salary) as total_sum_of_salaries
from countries as c
LEFT JOIN  towns as tw
ON c.id=tw.country_id
LEFT JOIN stadiums as s
ON s.town_id = tw.id
LEFT JOIN teams as t
ON t.stadium_id = s.id
LEFT JOIN players as p
ON p.team_id=t.id
GROUP BY c.name
ORDER BY total_count_of_players DESC, c.name
;


#10
DELIMITER $$
CREATE FUNCTION `udf_stadium_players_count` (stadium_name VARCHAR(30))
RETURNS INT
DETERMENISTIC

BEGIN


RETURN (
SELECT COUNT(p.id) as count
FROM stadiums as s
LEFT JOIN teams as t
ON t.stadium_id = s.id
LEFT JOIN players as p
ON p.team_id=t.id
WHERE s.name = stadium_name;
);
END;$$

DELIMITER ;

SELECT udf_stadium_players_count ('Jaxworks') as `count`; 




SELECT COUNT(p.id) as count
FROM stadiums as s
LEFT JOIN teams as t
ON t.stadium_id = s.id
LEFT JOIN players as p
ON p.team_id=t.id
WHERE s.name = 'Linklinks'
;