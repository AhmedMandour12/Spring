-- Customers and orders
SELECT c.customer_name, o.order_id
FROM customers c
FULL OUTER JOIN orders o
ON c.customer_id = o.customer_id;

-- Employees and projects
SELECT e.employee_name, p.project_name
FROM employees e
FULL OUTER JOIN projects p
ON e.project_id = p.project_id;

-- Products and suppliers
SELECT p.product_name, s.supplier_name
FROM products p
FULL OUTER JOIN suppliers s
ON p.supplier_id = s.supplier_id;

-- Students and courses
SELECT s.student_name, c.course_name
FROM enrollments e
FULL OUTER JOIN students s
ON e.student_id = s.student_id
FULL OUTER JOIN courses c
ON e.course_id = c.course_id;

-- Authors and books
SELECT a.author_name, b.book_title
FROM authors a
FULL OUTER JOIN books b
ON a.author_id = b.author_id;

-- Employees and departments
SELECT e.employee_name, d.department_name
FROM employees e
FULL OUTER JOIN departments d
ON e.department_id = d.department_id;

-- Transactions and payment methods
SELECT t.transaction_id, pm.payment_method_name
FROM transactions t
FULL OUTER JOIN payment_methods pm
ON t.payment_method_id = pm.payment_method_id;

-- Customers from two regions
SELECT r1.customer_name AS region1_customer,
       r2.customer_name AS region2_customer
FROM customers_region1 r1
FULL OUTER JOIN customers_region2 r2
ON r1.customer_id = r2.customer_id;