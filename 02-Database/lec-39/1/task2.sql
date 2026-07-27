-- Employees and departments
SELECT employee_name, department_name
FROM employees
JOIN departments
USING (department_id);

-- Orders and customers
SELECT order_id, customer_name
FROM orders
JOIN customers
USING (customer_id);

-- Products and suppliers
SELECT product_name, supplier_name
FROM products
JOIN suppliers
USING (supplier_id);

-- Students and courses
SELECT student_name, course_title
FROM students
JOIN enrollments
USING (student_id)
JOIN courses
USING (course_id);

-- Invoices and products
SELECT invoice_number, product_name
FROM invoices
JOIN products
USING (product_id);

-- Projects and employees
SELECT project_name, employee_name
FROM projects
JOIN employees
USING (project_id);

-- Authors and books
SELECT author_name, book_title
FROM authors
JOIN books
USING (author_id);

-- Sales orders and employees
SELECT order_id, employee_name
FROM sales_orders
JOIN employees
USING (employee_id);

-- Course schedules and instructors
SELECT schedule_time, instructor_name
FROM course_schedules
JOIN instructors
USING (instructor_id);

-- Transactions and account holders
SELECT transaction_id, account_holder_name
FROM transactions
JOIN accounts
USING (account_id);