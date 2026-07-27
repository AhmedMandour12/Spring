-- Employees and departments
SELECT e.employee_name, d.department_name
FROM employees e
LEFT OUTER JOIN departments d
ON e.department_id = d.department_id;

-- Products and categories
SELECT p.product_name, c.category_name
FROM products p
LEFT OUTER JOIN categories c
ON p.category_id = c.category_id;

-- Students and courses
SELECT s.student_name, c.course_name
FROM students s
LEFT OUTER JOIN enrollments e
ON s.student_id = e.student_id
LEFT OUTER JOIN courses c
ON e.course_id = c.course_id;

-- Orders and customers
SELECT o.order_id, c.customer_name
FROM orders o
LEFT OUTER JOIN customers c
ON o.customer_id = c.customer_id;

-- Departments and managers
SELECT d.department_name, m.manager_name
FROM departments d
LEFT OUTER JOIN managers m
ON d.manager_id = m.manager_id;

-- Books and authors
SELECT b.book_title, a.author_name
FROM books b
LEFT OUTER JOIN authors a
ON b.author_id = a.author_id;

-- Invoices and payments
SELECT i.invoice_id, p.payment_status
FROM invoices i
LEFT OUTER JOIN payments p
ON i.invoice_id = p.invoice_id;

-- Employees and projects
SELECT e.employee_name, pa.project_name
FROM employees e
LEFT OUTER JOIN projects_assigned pa
ON e.employee_id = pa.employee_id;