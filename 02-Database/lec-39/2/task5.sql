-- Employees with the highest salary
SELECT employee_name, salary
FROM employees
WHERE salary = (
    SELECT MAX(salary)
    FROM employees
);

-- Employees in the same department as Alice
SELECT employee_name
FROM employees
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE employee_name = 'Alice'
);

-- Product with the lowest price
SELECT *
FROM products
WHERE price = (
    SELECT MIN(price)
    FROM products
);

-- Department of the highest-paid employee
SELECT department_name
FROM departments
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE salary = (
        SELECT MAX(salary)
        FROM employees
    )
);

-- Manager of the most recently hired employee
SELECT employee_name
FROM employees
WHERE employee_id = (
    SELECT manager_id
    FROM employees
    WHERE hire_date = (
        SELECT MAX(hire_date)
        FROM employees
    )
);

-- Employee with salary equal to the average salary
SELECT employee_name, salary
FROM employees
WHERE salary = (
    SELECT AVG(salary)
    FROM employees
);

-- Orders with the earliest order date
SELECT *
FROM orders
WHERE order_date = (
    SELECT MIN(order_date)
    FROM orders
);

-- Employees earning more than employee 101
SELECT employee_name, salary
FROM employees
WHERE salary > (
    SELECT salary
    FROM employees
    WHERE employee_id = 101
);

-- Students with the same GPA as John Doe
SELECT student_name, gpa
FROM students
WHERE gpa = (
    SELECT gpa
    FROM students
    WHERE student_name = 'John Doe'
);

-- Books with the same price as the most expensive Science book
SELECT book_title, price
FROM books
WHERE price = (
    SELECT MAX(price)
    FROM books
    WHERE category = 'Science'
);