
-- 1. CHECK CONSTRAINT

-- Create Employees table with CHECK age >= 18
CREATE TABLE Employees (
    employee_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    age NUMBER CHECK (age >= 18)
);

-- Enforce salary between 3000 and 10000 in Staff table
CREATE TABLE Staff (
    staff_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    salary NUMBER(10,2) CHECK (salary BETWEEN 3000 AND 10000)
);

-- Add CHECK to existing Products table (price > 0)
ALTER TABLE Products ADD CONSTRAINT chk_price CHECK (price > 0);

-- Create Students with grade A-F only
CREATE TABLE Students (
    student_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    grade CHAR(1) CHECK (grade IN ('A','B','C','D','E','F'))
);

-- 2. ADDING CONSTRAINTS VIA ALTER TABLE

-- NOT NULL on email
ALTER TABLE Customers MODIFY (email NOT NULL);

-- UNIQUE on username
ALTER TABLE Users ADD CONSTRAINT uk_username UNIQUE (username);

-- FOREIGN KEY on Orders
ALTER TABLE Orders ADD CONSTRAINT fk_order_customer 
FOREIGN KEY (customer_id) REFERENCES Customers(id);

-- CHECK balance >= 0
ALTER TABLE Accounts ADD CONSTRAINT chk_balance CHECK (balance >= 0);

-- PRIMARY KEY on Departments
ALTER TABLE Departments ADD CONSTRAINT pk_departments PRIMARY KEY (dept_id);


-- 3. DROPPING CONSTRAINTS

-- Drop CHECK chk_salary
ALTER TABLE Employees DROP CONSTRAINT chk_salary;

-- Remove UNIQUE on email
ALTER TABLE Users DROP CONSTRAINT uk_email;

-- Drop PRIMARY KEY from Products
ALTER TABLE Products DROP CONSTRAINT pk_products;

-- Drop FOREIGN KEY fk_order_customer
ALTER TABLE Orders DROP CONSTRAINT fk_order_customer;

-- Remove NOT NULL from phone
ALTER TABLE Contacts MODIFY (phone NULL);

-- 4. RENAMING CONSTRAINTS

-- Rename CHECK chk_age to check_min_age
ALTER TABLE Students RENAME CONSTRAINT chk_age TO check_min_age;

-- Rename FOREIGN KEY fk_emp_dept to fk_employee_department
ALTER TABLE Employees RENAME CONSTRAINT fk_emp_dept TO fk_employee_department;

-- Rename PRIMARY KEY to pk_users_id
ALTER TABLE Users RENAME CONSTRAINT pk_users TO pk_users_id;

-- Rename UNIQUE to uk_user_name
ALTER TABLE Users RENAME CONSTRAINT uk_username TO uk_user_name;

-- 5. DISABLING CONSTRAINTS

ALTER TABLE Orders DISABLE CONSTRAINT fk_customer_order;

ALTER TABLE Products DISABLE ALL CONSTRAINTS;

ALTER TABLE Accounts DISABLE CONSTRAINT chk_balance;

ALTER TABLE Departments DISABLE CONSTRAINT pk_departments;


ALTER TABLE target_table DISABLE ALL CONSTRAINTS;

INSERT INTO target_table SELECT * FROM source_table;

ALTER TABLE target_table ENABLE ALL CONSTRAINTS;

-- Check disabled constraints
SELECT constraint_name, status 
FROM user_constraints 
WHERE table_name = 'TARGET_TABLE' 
AND status = 'DISABLED';

-- 6. ENABLING CONSTRAINTS

-- Enable FOREIGN KEY
ALTER TABLE Orders ENABLE CONSTRAINT fk_customer_order;

-- Re-enable all constraints on Products
ALTER TABLE Products ENABLE ALL CONSTRAINTS;

-- Enable CHECK on salary
ALTER TABLE Staff ENABLE CONSTRAINT chk_salary;

-- Enable PRIMARY KEY on Departments
ALTER TABLE Departments ENABLE CONSTRAINT pk_departments;

-- Enable only if currently disabled
DECLARE
    v_status VARCHAR2(20);
BEGIN
    SELECT status INTO v_status
    FROM user_constraints
    WHERE constraint_name = 'FK_CUSTOMER_ORDER'
    AND table_name = 'ORDERS';
    
    IF v_status = 'DISABLED' THEN
        EXECUTE IMMEDIATE 'ALTER TABLE Orders ENABLE CONSTRAINT fk_customer_order';
        DBMS_OUTPUT.PUT_LINE('Constraint enabled successfully');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Constraint already enabled');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Constraint not found');
END;
/

-- Alternative: Enable with exceptions
ALTER TABLE Orders ENABLE CONSTRAINT fk_customer_order;

-- Check if any exceptions occurred
SELECT * FROM exceptions;

-- VERIFY ALL CONSTRAINTS
SELECT 
    table_name,
    constraint_name,
    constraint_type,
    status,
    deferrable,
    deferred
FROM user_constraints
WHERE table_name IN ('EMPLOYEES','STAFF','PRODUCTS','STUDENTS','CUSTOMERS',
                     'USERS','ORDERS','ACCOUNTS','DEPARTMENTS','CONTACTS')
ORDER BY table_name, constraint_name;

-- Check column-level constraints
SELECT 
    c.table_name,
    c.constraint_name,
    c.constraint_type,
    col.column_name
FROM user_constraints c
JOIN user_cons_columns col ON c.constraint_name = col.constraint_name
WHERE c.table_name IN ('EMPLOYEES','STAFF','PRODUCTS','STUDENTS','CUSTOMERS',
                       'USERS','ORDERS','ACCOUNTS','DEPARTMENTS','CONTACTS')
ORDER BY c.table_name, c.constraint_name;

-- DISABLED CONSTRAINTS REPORT

SELECT 
    table_name,
    constraint_name,
    constraint_type,
    status
FROM user_constraints
WHERE status = 'DISABLED'
ORDER BY table_name;

-- CLEANUP

DROP TABLE Orders CASCADE CONSTRAINTS;
DROP TABLE Customers CASCADE CONSTRAINTS;
DROP TABLE Employees CASCADE CONSTRAINTS;
DROP TABLE Staff CASCADE CONSTRAINTS;
DROP TABLE Products CASCADE CONSTRAINTS;
DROP TABLE Students CASCADE CONSTRAINTS;
DROP TABLE Users CASCADE CONSTRAINTS;
DROP TABLE Accounts CASCADE CONSTRAINTS;
DROP TABLE Departments CASCADE CONSTRAINTS;
DROP TABLE Contacts CASCADE CONSTRAINTS;
