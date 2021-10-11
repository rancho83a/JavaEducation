#1
SELECT title from books
WHERE substr(title, 1,3) = 'The'
ORDER BY id;

#2
SELECT replace( title, 'The', '***') from books
WHERE substr(title, 1, 3) = 'The'
ORDER BY id;

#3

#4
select CONCAT_WS(' ', first_name, last_name) AS 'Full Name',timestampdiff(DAY, born, died) AS 'Days Lived'
FROM authors;