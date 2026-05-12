--create Employees table wiith column ( EmployeeID, FirstName, LastName, Department, Salary )

CREATE TABLE EMPLOYEES (
EmployeeID INT ,
FirstName  VARCHAR(100),
LastName VARCHAR (100),
Department VARCHAR(400),
Salary DECIMAL (10,2) 
);


INSERT ALL
	INTO EMPLOYEES VALUES (101, 'John1', 'Doe1', 'HR', 20000)
    INTO EMPLOYEES VALUES (102, 'John2', 'Doe2', 'IT', 50000)
    INTO EMPLOYEES VALUES (103, 'John3', 'Doe3', 'CS', 40000)
    INTO EMPLOYEES VALUES (104, 'John4', 'Doe4', 'IT', 10000)
    INTO EMPLOYEES VALUES (105, 'John5', 'Doe5', 'ZX', 30000)
    SELECT * FROM DUAL;
SELECT *FROM EMPLOYEES; --

--Update the salary of an employee to 600000 with EmployeeID     101.

UPDATE EMPLOYEES
SET Salary =600000 
WHERE EmployeeID =101;
SELECT *FROM EMPLOYEES WHERE EmployeeID =101; --

--Delete a record of an employee who Department = 101.
DELETE FROM EMPLOYEES 
WHERE EmployeeID =101; 

--Retrieve all employees in the IT department.
SELECT * FROM EMPLOYEES 
WHERE Department = 'IT';

--select * data from table but conatination  FirstName, LastName as one column
SELECT 
    FirstName || ''||
    LastName
    AS FULLNAME
FROM EMPLOYEES;

 
	