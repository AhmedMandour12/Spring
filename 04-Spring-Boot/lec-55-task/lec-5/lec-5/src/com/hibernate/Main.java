package com.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Course;
import com.model.Student;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class).configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		List<Student> students = new ArrayList();

		students.add(new Student("Ahmed", 20));
		students.add(new Student("Mohamed", 22));
		students.add(new Student("Ali", 21));

		List<Course> courses = new ArrayList<>();

		courses.add(new Course("Java"));
		courses.add(new Course("Spring Boot"));
		courses.add(new Course("Hibernate"));

		// <=======>save from model-1(owningSide)<=======

		courses.forEach(course -> session.save(course));
		students.forEach(stu -> stu.setCourses(courses));
		students.forEach(stu -> session.save(stu));
		System.out.println(students);
		// =======>save from model-2(inverse side)<=======

		students.forEach(stu -> session.save(stu));
		courses.forEach(co -> co.setStudents(students));
		courses.forEach(co -> session.save(co));
		students.forEach(stu -> stu.setCourses(courses));
		students.forEach(stu -> session.save(stu));
		System.out.println(students);

		// =======>persist from model-1(owningSide)<=======

		students.forEach(stu -> stu.setCourses(courses));
		students.forEach(stu -> session.persist(stu));
		System.out.println(students);

		// =====> persist from model-2(inverse side)<=====

		courses.forEach(co -> co.setStudents(students));
		students.forEach(stu -> stu.setCourses(courses));
		courses.forEach(co -> session.persist(co));
		System.out.println(courses);

		transaction.commit();
		System.out.println("END PROGRAM");
		factory.close();
		session.close();

	}

}
