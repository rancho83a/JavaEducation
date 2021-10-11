#1
DELIMITER $$
CREATE PROCEDURE `usp_get_employees_salary_above_35000` ()
BEGIN
SELECT first_name, last_name
FROM employees
WHERE salary > 35000
ORDER BY first_name, last_name, employee_id;

END $$
DELIMITER ;

CALL `usp_get_employees_salary_above_35000` ();

#2
DELIMITER $$
CREATE PROCEDURE `usp_get_employees_salary_above` (param DECIMAL(19,4))
BEGIN
SELECT first_name, last_name
FROM employees
WHERE salary >= param
ORDER BY first_name, last_name, employee_id;

END $$
DELIMITER ;

CALL `usp_get_employees_salary_above` (45000);



#3
DELIMITER $$
CREATE PROCEDURE `usp_get_towns_starting_with` (param VARCHAR(100))
BEGIN
SELECT `name` as town_name
FROM towns
WHERE `name` LIKE CONCAT(param,'%')
ORDER BY town_name;
END $$
DELIMITER ;

CALL `usp_get_towns_starting_with` ('b');

#4
DELIMITEr $$
CREATE PROCEDURE `usp_get_employees_from_town` (town_name VARCHAR(50))
BEGIN
SELECT first_name, last_name
FROM employees as e
JOIN addresses as a
USING(address_id)
JOIN towns as t
USING(town_id)
WHERE t.name= town_name
ORDER BY first_name, last_name, employee_id;
END $$
DELIMITER ;

CALL `usp_get_employees_from_town`('Sofia');


#5
DELIMITEr $$
CREATE FUNCTION `ufn_get_salary_level` (salary DECIMAL)

RETURNS VARCHAR(10)
deterministic
BEGIN
RETURN (
 CASE
 
 WHEN salary<30000 THEN 'Low'
 WHEN salary>50000 THEN 'High'
 ELSE 'Average'
 END
); 
END $$
DELIMITEr ;

SELECT 4500 as salary, `ufn_get_salary_level` (45000) as salary_level;



#6
DELIMITEr $$
CREATE PROCEDURE `usp_get_employees_by_salary_level` (level_salary VARCHAR(10))
BEGIN
SELECT first_name, last_name
FROM employees as e

WHERE `ufn_get_salary_level` (e.salary) = level_salary
ORDER BY first_name DESC, last_name DESC;
END $$
DELIMITER ;

CALL `usp_get_employees_by_salary_level`('High');


#7

DELIMITER $$
CREATE FUNCTION `ufn_is_word_comprised`(set_of_letters varchar(50), word varchar(50))
RETURNS INT
deterministic

BEGIN
	DECLARE current_index INT;
	DECLARE current_symbol VARCHAR(1);
	SET current_index=1;

	label_loop:	WHILE current_index <= CHAR_LENGTH(word) DO
				SET current_symbol = SUBSTR(word, current_index,1);
				IF LOCATE(current_symbol, set_of_letters)=0 THEN RETURN 0;
                END IF;
				SET current_index = current_index+1;
	END WHILE label_loop;
	RETURN 1;
END; $$
DELIMITER ;
SELECT ufn_is_word_comprised ('sasassoi', 'Sofia');


#7
DELIMITER $$
CREATE FUNCTION `ufn_is_word_comprised`(set_of_letters varchar(50), word varchar(50))
RETURNS BOOLEAN
deterministic

BEGIN
	
	RETURN (SELECT word REGEXP CONCAT('[',set_of_letters,']'));
END; $$
DELIMITER ;
SELECT ufn_is_word_comprised ('sa sassoi', 'Sofia');


#8

DELIMITER $$
CREATE PROCEDURE `usp_get_holders_full_name` ()
BEGIN
SELECT CONCAT_WS(' ', first_name, last_name) as full_name
FROM account_holders
ORDER BY full_name, id;
END;$$
DELIMITER ; 
CALL `usp_get_holders_full_name`;

#9

DELIMITER $$
CREATE PROCEDURE `usp_get_holders_with_balance_higher_than` (num DECIMAL)
BEGIN
SELECT  ah.first_name, ah.last_name
FROM account_holders as ah
JOIN accounts a
ON ah.id=a.account_holder_id
GROUP BY a.account_holder_id
HAVING SUM(a.balance)>num
ORDER BY a.account_holder_id;
END;$$
DELIMITER ; 
CALL `usp_get_holders_with_balance_higher_than`(7000);


#10
DELIMITER $$
CREATE FUNCTION `ufn_calculate_future_value`(sum DECIMAL(19,4), rate double, years INT)
RETURNS DECIMAL(19,4)
deterministic
BEGIN
	
	RETURN sum*(POW((1+rate),years));
END; $$
DELIMITER ;
SELECT ufn_calculate_future_value (1000, 0.5,5);


#11
DELIMITER $$
CREATE PROCEDURE `usp_calculate_future_value_for_account` (account_id INT, interest_rate DECIMAL(19,4))
BEGIN
SELECT  a.id as account_id,
		ah.first_name, ah.last_name,
        a.balance as current_balance,
       (
       ufn_calculate_future_value (a.balance, interest_rate,5)
       ) as balance_in_5_years
        
FROM accounts as a 
JOIN account_holders as ah
ON ah.id=a.account_holder_id
WHERE a.id=account_id;
END; $$
DELIMITER ; 

CALL `usp_calculate_future_value_for_account`(1,0.1);


#12
DELIMITER $$

CREATE PROCEDURE `usp_deposit_money`(account_id INT, money_amount DECIMAL(19,4))
BEGIN
START TRANSACTION;
IF
	(SELECT COUNT(*) FROM accounts WHERE id = account_id ) = 0
OR 
	money_amount <=0 
THEN ROLLBACK;
ELSE
	UPDATE accounts
	SET balance = balance  + money_amount
	WHERE account_id = id;
END IF;

END; $$
DELIMITER ; 

CALL `usp_deposit_money`(1,-10);


#13
DELIMITER $$

CREATE PROCEDURE `usp_withdraw_money`(account_id INT, money_amount DECIMAL(19,4))
BEGIN
START TRANSACTION;
IF
	(SELECT COUNT(*) FROM accounts WHERE id = account_id ) = 0
OR  	money_amount <=0 
OR  	money_amount >  (SELECT balance FROM accounts WHERE id = account_id) 
THEN ROLLBACK;
ELSE
	UPDATE accounts
	SET balance = balance  - money_amount
	WHERE account_id = id;
END IF;

END; $$
DELIMITER ; 

CALL `usp_withdraw_money`(1,10);

#14
DELIMITER $$

CREATE PROCEDURE `usp_transfer_money`(from_account_id INT, to_account_id  INT, amount DECIMAL(19,4))
BEGIN
START TRANSACTION;
IF
	(SELECT COUNT(*) FROM accounts WHERE id = from_account_id ) = 0
    OR
	(SELECT COUNT(*) FROM accounts WHERE id = to_account_id  ) = 0
OR  	amount <=0 
OR  	 (SELECT balance FROM accounts WHERE id = from_account_id) < amount 
OR from_account_id = to_account_id
THEN ROLLBACK;
ELSE
	UPDATE accounts
	SET balance = balance  - amount
	WHERE id=from_account_id ;
    
    UPDATE accounts
	SET balance = balance  + amount
	WHERE id=to_account_id ;
    
    
END IF;

END; $$
DELIMITER ; 

CALL `usp_transfer_money`(1,2,100);

#15
CREATE  TABLE logs(
log_id INT PRIMARY KEY AUTO_INCREMENT,
 account_id INT,
 old_sum DECIMAL(19,4),
 new_sum DECIMAL (19,4)
 );

DELIMITER $$
CREATE  TRIGGER `accounts_AFTER_UPDATE` 
AFTER UPDATE
 ON `accounts` 
 FOR EACH ROW
BEGIN
INSERT INTO `logs` (account_id, old_sum, new_sum)
VALUES(OLD.id, OLD.balance, NEW.balance);
END; $$
DELIMITER ;



#16
CREATE TABLE notification_emails(
id INT PRIMARY KEY AUTO_INCREMENT,
recipient INT, 
`subject` TEXT,
`body` TEXT
);
DROP TRIGGER IF EXISTS `soft_uni`.`tr_inserted_logs`;
DELIMITER $$
CREATE  TRIGGER `tr_inserted_logs` 
AFTER INSERT
 ON `logs` 
 FOR EACH ROW
BEGIN
INSERT INTO `notification_emails` (`recipient`, `subject`, `body`)
VALUES(
NEW.account_id,
CONCAT('Balance change for account: ',NEW.account_id),
concat_ws(' ', 'On', DATE_FORMAT(now(), "%b %d %Y at %r"), 'your balance was changed from', NEW.old_sum,'to',NEW.new_sum ));
END; $$
DELIMITER ;



