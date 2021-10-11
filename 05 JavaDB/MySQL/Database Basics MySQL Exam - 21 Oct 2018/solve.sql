#1
INSERT INTO travel_cards(card_number, job_during_journey, colonist_id, journey_id)

SELECT 
(CASE 
WHEN birth_date>'1980-01-01' THEN  CONCAT(YEAR(c.birth_date),DAY(c.birth_date), LEFT(c.ucn,4))
ELSE CONCAT(YEAR(c.birth_date),MONTH(c.birth_date), RIGHT(c.ucn,4))
END) as card_number,

(
CASE 
WHEN id %2 =0 THEN  'Pilot'
WHEN id %3 =0 THEN  'Cook'
ELSE 'Engineer'
END) as job_during_journey,

c.id, 
(LEFT(c.ucn,1) )as journey_id

FROM colonists as c
WHERE c.id BETWEEN 96 AND 100;


SELECT 
(CASE 
WHEN birth_date>'1980-01-01' THEN  CONCAT(YEAR(birth_date),DAY(birth_date), RIGHT(ucn,4))
ELSE CONCAT(YEAR(birth_date),MONTH(birth_date), RIGHT(ucn,4))
END) as card_number
FROM colonists
WHERE id BETWEEN 96 AND 100;

SELECT 
(CASE 
WHEN id %2 =0 THEN  'Pilot'
WHEN id %3 =0 THEN  'Cook'
ELSE 'Engineer'
END) 
FROM colonists
WHERE id BETWEEN 96 AND 100;

#2
UPDATE journeys
SET purpose = 
CASE  

WHEN id % 2=0 THEN 'Medical'
WHEN id % 3=0 THEN 'Technical'
WHEN id % 5=0 THEN 'Educational'
WHEN id % 7=0 THEN 'Military'


END
WHERE id % 2=0 OR id % 3=0 OR id % 5=0 OR id % 7=0;

#3 
DELETE FROM 
colonists as c
WHERE c.id not in (SELECT colonist_id FROM travel_cards);


#4
SELECT card_number, job_during_journey
FROM travel_cards
ORDER BY card_number;

#5
SELECT id, CONCAT(first_name, ' ', last_name) as full_name, ucn
FROM colonists
ORDER BY first_name,last_name, id;

#6
SELECT id, journey_start, journey_end
FROM journeys
WHERE purpose ='Military'
ORDER BY journey_start;

#7
SELECT c.id, CONCAT(first_name, ' ', last_name) as full_name
FROM colonists as c
JOIN travel_cards as tc
ON tc.colonist_id=c.id
WHERE tc.job_during_journey = 'Pilot'
ORDER BY c.id;

#8
SELECT COUNT(*) as count
FROM colonists as c
JOIN travel_cards as tc
ON tc.colonist_id=c.id
JOIN journeys as j
ON j.id = tc.journey_id

WHERE j.purpose = 'Technical';

#9
SELECT s.name as spaceship_name, sp.name as spaceport_name
FROM spaceships as s
JOIN journeys as j
ON j.spaceship_id = s.id
JOIN spaceports as sp
ON sp.id = j.destination_spaceport_id
ORDER BY light_speed_rate DESC
LIMIT 1;

#10
SELECT DISTINCT ss.name, ss.manufacturer
 from spaceships as ss
 JOIN journeys as j
 ON j.spaceship_id=ss.id
 JOIN travel_cards as tc
 ON tc.journey_id = j.id
 JOIN colonists as c
 ON c.id=tc.colonist_id
 WHERE tc.job_during_journey = 'Pilot' AND YEAR(c.birth_date)>=1989
 ORDER BY ss.name;
 
 #11
 SELECT p.name as planet_name, sp.name as spaceport_name

 FROM planets as p
 JOIN spaceports as sp
 ON p.id = sp.planet_id
 JOIN journeys as j
 ON sp.id=j.destination_spaceport_id
WHERE j.purpose = 'Educational'
  ORDER BY sp.name DESC
 ;
 
 #12
 SELECT p.name as planet_name, count(j.id) as journeys_count
 FROM planets as p
  JOIN spaceports as sp
 ON p.id = sp.planet_id
  JOIN journeys as j
 ON sp.id=j.destination_spaceport_id
 GROUP by p.name
 ORDER BY journeys_count DESC, p.name
 ;
 
 #13
 SELECT j.id, p.name as planet_name, sp.name as spasceport_name, j.purpose
 FROM journeys as j
 join spaceports as sp
 ON sp.id = j.destination_spaceport_id
 JOIN planets as p
 ON p.id=sp.planet_id
 ORDER BY datediff(j.journey_end, j.journey_start)  
 LIMIT 1;
 
 #14
 SELECT job_during_journey as job_name
 FROM travel_cards as tc
 JOIN journeys as j
 ON j.id=tc.journey_id
 WHERE j.id = (
  SELECT id
 FROM journeys as j
  ORDER BY datediff(j.journey_end, j.journey_start)  DESC
LIMIT 1
 )
  GROUP BY tc.job_during_journey
 ORDER BY COUNT(*)
LIMIT 1
 ;
 
 #15
 DELIMITER $$
 CREATE FUNCTION `udf_count_colonists_by_destination_planet`(planet_name VARCHAR (30))
RETURNS INTEGER
DETERMINISTIC
BEGIN

RETURN (
 SELECT COUNt(*)
 FROM planets as p
  JOIN spaceports as sp
 ON p.id = sp.planet_id
  JOIN journeys as j
 ON sp.id=j.destination_spaceport_id
 JOIN travel_cards as tc
 ON j.id= tc.journey_id
 join colonists as c
 ON c.id = tc.colonist_id
  Where p.name = planet_name
);
END;$$
 DELIMITER ;
 SELECT p.name, udf_count_colonists_by_destination_planet('Otroyphus') AS count
FROM planets AS p
WHERE p.name = 'Otroyphus';
 
  SELECT COUNt(*)
 FROM planets as p
  JOIN spaceports as sp
 ON p.id = sp.planet_id
  JOIN journeys as j
 ON sp.id=j.destination_spaceport_id
 JOIN travel_cards as tc
 ON j.id= tc.journey_id
 join colonists as c
 ON c.id = tc.colonist_id
 
 Where p.name = 'Otroyphus'
 ;
 
 
 #16
 DELIMITEr $$
 CREATE PROCEDURE `udp_modify_spaceship_light_speed_rate`(spaceship_name VARCHAR(50), light_speed_rate_increse INT)
BEGIN
START TRANSACTION;
IF
	(SELECT COUNT(*) FROM spaceships WHERE `name` = spaceship_name) = 0
  
THEN 
 SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.';
ROLLBACK;
ELSE
	UPDATE spaceships
	SET  light_speed_rate= light_speed_rate_increse+light_speed_rate
	WHERE`name` = spaceship_name ;  
END IF;

END; $$
DELIMITER ; 

CALL udp_modify_spaceship_light_speed_rate ('Na Pesho koraba', 1914);
SELECT name, light_speed_rate FROM spacheships WHERE name = 'Na Pesho koraba';

CALL udp_modify_spaceship_light_speed_rate ('USS Templar', 5);
SELECT name, light_speed_rate FROM spaceships WHERE name = 'USS Templar'