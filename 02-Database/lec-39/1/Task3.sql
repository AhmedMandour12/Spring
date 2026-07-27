-- Employees and managers
SELECT e.employee_name, m.employee_name AS manager_name
FROM employees e
JOIN employees m
ON e.manager_id = m.employee_id;

-- Customers and salespersons
SELECT c.name AS customer_name,
       e.name AS salesperson_name
FROM customers c
JOIN employees e
ON c.salesperson_id = e.employee_id;

-- Orders and products
SELECT o.order_id,
       od.product_id
FROM orders o
JOIN order_details od
ON o.order_id = od.order_id;

-- Students and instructors
SELECT s.name AS student_name,
       i.name AS instructor_name
FROM students s
JOIN instructors i
ON s.instructor_id = i.instructor_id;

-- Employee salaries and department budgets
SELECT e.employee_name,
       e.salary,
       d.budget
FROM employees e
JOIN departments d
ON e.department_id = d.department_id;

-- Projects and tasks
SELECT p.name AS project_name,
       t.name AS task_name
FROM projects p
JOIN tasks t
ON p.project_id = t.project_id;

-- Courses and exams
SELECT c.course_name,
       c.course_date,
       e.exam_date
FROM courses c
JOIN exams e
ON c.course_id = e.course_id;

-- Products and categories
SELECT p.name AS product_name,
       c.name AS category_name
FROM products p
JOIN categories c
ON p.category_id = c.category_id;

-- Books and publishers
SELECT b.title AS book_title,
       p.publisher_name
FROM books b
JOIN publishers p
ON b.publisher_id = p.publisher_id;

-- Employees and department locations
SELECT e.employee_name,
       d.location
FROM employees e
JOIN departments d
ON e.department_id = d.department_id;