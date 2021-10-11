#2
INSERT INTO cards(card_number, card_status, bank_account_id)
(
SELECT reverse(full_name), 'Active', id 
FROM clients as c
WHERE c.id BETWEEN 191 AND 200
);

#3

UPDATE employees_clients 
JOIN 
(
SELECT employee_id 
FROM  employees_clients 
GROUP by employee_id
ORDER BY (COUNT(*)) , employee_id 
LIMIT 1
) as s
SET employees_clients.employee_id = s.employee_id 

WHERE employees_clients.employee_id=employees_clients.client_id;

#4
DELETE  FROM employees 
WHERE id  not in (SELECT employee_id FROM employees_clients);

#5
SELECT id, full_name
FROM clients
ORDER BY id;

#6
SELECT id, CONCAT(first_name, ' ', last_name)as full_name, CONCAT('$',salary) as salary, started_on
FROM employees
WHERE salary >= 100000 AND started_on >= '2018-01-01'
ORDER BY  salary DESC, id;

#7
SELECT c.id, CONCAT(c.card_number,' : ', cl.full_name) as card_token
FROM cards as c
JOIN bank_accounts as ba
ON c.bank_account_id=ba.id
JOIN clients as cl
on cl.id = ba.client_id
ORDER BY c.id DESC;

#8
SELECT CONCAT (first_name, ' ', last_name) as `name`, started_on, COUNT(*) as count_of_clients
FROM employees as e
JOIN employees_clients as ec
on e.id=ec.employee_id
GROUP BY ec.employee_id
ORDER by count_of_clients DESC, e.id
LIMIT 5;

#9
SELECT `name`, Count(c.id) as count_of_cards
FROM branches as b
LEFT JOIN employees AS e
ON b.id = e.branch_id
LEFT JOIN employees_clients as ec
ON e.id=ec.employee_id
LEFT JOIN clients as cl
ON ec.client_id=cl.id
LEFT JOIN bank_accounts as ba
ON cl.id = ba.client_id
LEFT JOIN cards as c
ON ba.id=c.bank_account_id
GROUP BY b.id
ORDER BY count_of_cards DESC, b.name;


#10
DELIMITER $$
CREATE FUNCTION `udf_client_cards_count`(name VARCHAR(30)) 
RETURNS INTEGER
DETERMINISTIC
BEGIN

RETURN (
SELECT   COUNT(c.id)
FROM clients as cl
JOIN bank_accounts as ba
ON cl.id = ba.client_id
JOIN cards as c
ON ba.id=c.bank_account_id
GROUP BY full_name
HAVING full_name = name
);
END; $$
DELIMITER ;

SELECT c.full_name, udf_client_cards_count('Baxy David') as `cards` FROM clients c
WHERE c.full_name = 'Baxy David';

SELECT   COUNT(c.id)
FROM clients as cl
JOIN bank_accounts as ba
ON cl.id = ba.client_id
JOIN cards as c
ON ba.id=c.bank_account_id
GROUP BY full_name
HAVING full_name = 'Baxy David'
;



#11
DELIMITER $$
CREATE PROCEDURE `udp_clientinfo`(fullname VARCHAR(50))
BEGIN

SELECT c.full_name, c.age, ba.account_number, CONCAT('$',ba.balance) as balance
FROM clients as c
JOIN bank_accounts as ba
ON ba.client_id=c.id
WHERE c.full_name=fullname;

END; $$
DELIMITER ;

CALL udp_clientinfo('Hunter Wesgate');



SELECT c.full_name, c.age, ba.account_number, CONCAT('$',ba.balance) as balance
FROM clients as c
JOIN bank_accounts as ba
ON ba.client_id=c.id
WHERE c.full_name='Hunter Wesgate'
;