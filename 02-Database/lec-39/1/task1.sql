-- Employees and departments
SELECT employee_name, department_name
FROM employees
NATURAL JOIN departments;

-- Orders and customers
SELECT order_id, customer_name
FROM orders
NATURAL JOIN customers;

-- Students and courses
SELECT student_name, course_name
FROM students
NATURAL JOIN enrollments
NATURAL JOIN courses;

-- Projects and employees
SELECT project_name, employee_name
FROM projects
NATURAL JOIN project_assignments
NATURAL JOIN employees;

-- Invoices and products
SELECT invoice_id, product_name, quantity
FROM invoices
NATURAL JOIN invoice_details
NATURAL JOIN products;

-- Books and authors
SELECT book_title, author_name
FROM books
NATURAL JOIN authors;

-- Class schedules and instructors
SELECT class_name, schedule_time, instructor_name
FROM class_schedules
NATURAL JOIN instructors;

-- Suppliers and products
SELECT supplier_name, product_name
FROM suppliers
NATURAL JOIN products;

-- Customer orders and shipping
SELECT order_id, customer_name, shipping_address, shipping_date
FROM orders
NATURAL JOIN customers
NATURAL JOIN shipping;

-- Employees and job titles
SELECT employee_name, job_title
FROM employees
NATURAL JOIN jobs;