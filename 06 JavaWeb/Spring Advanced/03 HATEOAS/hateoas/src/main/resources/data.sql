INSERT INTO students (id,age, name)
VALUES
       (1,33,'Pesho'),
       (2,22,'Anna');


INSERT INTO courses (id, name, enabled, price)
VALUES
    (1,'Spring dummies',0,50),
    (2,'Spring for pros',1,100);

INSERT INTO orders (id, course_id, student_id)
VALUES
    (1,1,1),
    (2,2,1),
    (3,2,2);