#1
CREATE TABLE mountains(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) 
);

CREATE TABLE peaks(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40),
mountain_id INT NOT NULL,
CONSTRAINT fk_peaks_mountains
FOREIGN KEY (mountain_id)
REFERENCES mountains(id)
);

#2
SELECT v.driver_id, v.vehicle_type, CONCAT(first_name, ' ', last_name) as driver_name 
FROM campers as c
JOIN vehicles as v
on c.id=v.driver_id;

#3
SELECT starting_point, end_point, leader_id, CONCAT(first_name, ' ', last_name ) as `leader_name`
 FROM routes AS r
JOIN campers as c
on leader_id = c.id
ORDER BY leader_id;



#4
CREATE TABLE mountains(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) 
);

CREATE TABLE peaks(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40),
mountain_id INT NOT NULL,
CONSTRAINT fk_peaks_mountains
FOREIGN KEY (mountain_id)
REFERENCES mountains(id)
ON DELETE CASCADE
);


#5
CREATE TABLE clients(
id INT PRIMARY KEY AUTO_INCREMENT,
client_name VARCHAR(100)
);

CREATE TABLE projects(
id INT PRIMARY KEY AUTO_INCREMENT,
client_id INT,
project_lead_id INT,
CONSTRAINT fk_projects_clients
FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE employees(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(30),
last_name VARCHAR(30),
project_id INT,
CONSTRAINT fk_empl_proj
FOREIGN KEY (project_id) REFERENCES projects(id)
);

ALTER TABLE projects
ADD CONSTRAINT fk_proj_emp
FOREIGN KEY (project_lead_id) REFERENCES employees(id);



