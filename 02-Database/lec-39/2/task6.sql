-- Employees earning more than at least one employee in department 10
SELECT employee_name, salary
FROM employees
WHERE salary > ANY (
    SELECT salary
    FROM employees
    WHERE department_id = 10
);

-- Employees earning less than all employees in department 20
SELECT employee_name, salary
FROM employees
WHERE salary < ALL (
    SELECT salary
    FROM employees
    WHERE department_id = 20
);

-- Products with the same price as any Electronics product
SELECT product_name, price
FROM products
WHERE price IN (
    SELECT price
    FROM products
    WHERE category = 'Electronics'
);

-- Customers who ordered products priced over $1000
SELECT customer_name
FROM customers
WHERE customer_id IN (
    SELECT o.customer_id
    FROM orders o
    JOIN order_details od
    ON o.order_id = od.order_id
    JOIN products p
    ON od.product_id = p.product_id
    WHERE p.price > 1000
);

-- Employees sharing job titles
SELECT employee_name, job_title
FROM employees
WHERE job_title IN (
    SELECT job_title
    FROM employees
    GROUP BY job_title
    HAVING COUNT(*) > 1
);

-- Departments with more than one employee
SELECT department_name
FROM departments
WHERE department_id IN (
    SELECT department_id
    FROM employees
    GROUP BY department_id
    HAVING COUNT(*) > 1
);

-- Orders from customers in shared cities
SELECT *
FROM orders
WHERE customer_id IN (
    SELECT customer_id
    FROM customers
    WHERE city IN (
        SELECT city
        FROM customers
        GROUP BY city
        HAVING COUNT(*) > 1
    )
);

-- Books by authors with multiple books
SELECT book_title
FROM books
WHERE author_id IN (
    SELECT author_id
    FROM books
    GROUP BY author_id
    HAVING COUNT(*) > 1
);

-- Students enrolled in Dr. Smith's courses
SELECT student_name
FROM students
WHERE student_id IN (
    SELECT student_id
    FROM enrollments
    WHERE course_id IN (
        SELECT course_id
        FROM courses
        WHERE instructor_name = 'Dr. Smith'
    )
);

-- Employees with salaries matching department 30
SELECT employee_name, salary
FROM employees
WHERE salary IN (
    SELECT salary
    FROM employees
    WHERE department_id = 30
);