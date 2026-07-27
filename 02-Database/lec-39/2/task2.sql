-- Departments and employees
SELECT d.department_name, e.employee_name
FROM employees e
RIGHT OUTER JOIN departments d
ON e.department_id = d.department_id;

-- Customers and orders
SELECT c.customer_name, o.order_id
FROM orders o
RIGHT OUTER JOIN customers c
ON o.customer_id = c.customer_id;

-- Courses and students
SELECT c.course_name, s.student_name
FROM enrollments e
RIGHT OUTER JOIN courses c
ON e.course_id = c.course_id
LEFT JOIN students s
ON e.student_id = s.student_id;

-- Projects and employees
SELECT p.project_name, e.employee_name
FROM employees e
RIGHT OUTER JOIN projects p
ON e.project_id = p.project_id;

-- Payment methods and transactions
SELECT pm.payment_method_name, t.transaction_id
FROM transactions t
RIGHT OUTER JOIN payment_methods pm
ON t.payment_method_id = pm.payment_method_id;

-- Authors and books
SELECT a.author_name, b.book_title
FROM books b
RIGHT OUTER JOIN authors a
ON b.author_id = a.author_id;

-- Categories and products
SELECT c.category_name, p.product_name
FROM products p
RIGHT OUTER JOIN categories c
ON p.category_id = c.category_id;

-- Dorm rooms and students
SELECT d.room_number, s.student_name
FROM students s
RIGHT OUTER JOIN dorm_rooms d
ON s.room_id = d.room_id;