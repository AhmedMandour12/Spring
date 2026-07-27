-- Employees earning above average salary
SELECT employee_name, salary
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
);

-- Customers with the highest number of orders
SELECT customer_name
FROM customers
WHERE customer_id IN (
    SELECT customer_id
    FROM orders
    GROUP BY customer_id
    HAVING COUNT(*) = (
        SELECT MAX(order_count)
        FROM (
            SELECT COUNT(*) AS order_count
            FROM orders
            GROUP BY customer_id
        )
    )
);

-- Products priced higher than any Accessories product
SELECT product_name, price
FROM products
WHERE price > ANY (
    SELECT price
    FROM products
    WHERE category = 'Accessories'
);

-- Employees in the same department as John Smith
SELECT employee_name
FROM employees
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE employee_name = 'John Smith'
);

-- Orders from customers in New York
SELECT *
FROM orders
WHERE customer_id IN (
    SELECT customer_id
    FROM customers
    WHERE city = 'New York'
);

-- Departments with no employees
SELECT department_name
FROM departments d
WHERE NOT EXISTS (
    SELECT 1
    FROM employees e
    WHERE e.department_id = d.department_id
);

-- Students not enrolled in any course
SELECT student_name
FROM students s
WHERE NOT EXISTS (
    SELECT 1
    FROM enrollments e
    WHERE e.student_id = s.student_id
);

-- Second highest salary
SELECT MAX(salary) AS second_highest_salary
FROM employees
WHERE salary < (
    SELECT MAX(salary)
    FROM employees
);

-- Products priced above average
SELECT product_name, price
FROM products
WHERE price > (
    SELECT AVG(price)
    FROM products
);

-- Customers who ordered all category A products
SELECT customer_name
FROM customers c
WHERE NOT EXISTS (
    SELECT product_id
    FROM products p
    WHERE p.category = 'A'
      AND NOT EXISTS (
          SELECT 1
          FROM orders o
          JOIN order_details od
          ON o.order_id = od.order_id
          WHERE o.customer_id = c.customer_id
            AND od.product_id = p.product_id
      )
);