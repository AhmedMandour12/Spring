package com.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Course;
import com.model.Frinds;
import com.model.Player;
import com.model.Post;
import com.model.Student;
import com.model.User;
import com.model.userDetails;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration()
//				.addAnnotatedClass(Player.class)
				.addAnnotatedClass(User.class).addAnnotatedClass(userDetails.class).addAnnotatedClass(Frinds.class)
				.addAnnotatedClass(Post.class).configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		/*
		 * ----------Lec4-Task1---------- Player player1 = new Player(1L, "Ahmed", 9,
		 * true); session.save(player1); Player player2 = session.get(Player.class, 1L);
		 * System.out.println(player2);
		 */
		/* ----------Lec5---------- */
		/*
		 * List<Student> students = new ArrayList();
		 * 
		 * students.add(new Student("Ahmed", 20)); students.add(new Student("Mohamed",
		 * 22)); students.add(new Student("Ali", 21));
		 * 
		 * List<Course> courses = new ArrayList<>();
		 * 
		 * courses.add(new Course("Java")); courses.add(new Course("Spring Boot"));
		 * courses.add(new Course("Hibernate"));
		 */
		// <=======>save from model-1(owningSide)<=======
		/*
		 * courses.forEach(course -> session.save(course));
		 * students.forEach(stu->stu.setCourses(courses));
		 * students.forEach(stu->session.save(stu));
		 */
		// =======>save from model-2(inverse side)<=======
		/*
		 * students.forEach(stu->session.save(stu));
		 * courses.forEach(co->co.setStudents(students));
		 * courses.forEach(co->session.save(co));
		 * students.forEach(stu->stu.setCourses(courses));
		 * students.forEach(stu->session.save(stu));
		 */
		// =======>persist from model-1(owningSide)<=======
		/*
		 * students.forEach(stu->stu.setCourses(courses));
		 * students.forEach(stu->session.persist(stu));
		 */

		// =====> persist from model-2(inverse side)<=====

		/*
		 * courses.forEach(co -> co.setStudents(students)); students.forEach(stu
		 * ->stu.setCourses(courses)); courses.forEach(co -> session.persist(co));
		 */
		
		                           /*   Task-lec-6  */
		                          /*Without cascade*/
		
/*   1-  Add User with UserDetails
		User user = new User( "Ahmed", 22);
		userDetails details = new userDetails("Cairo", 123456789);
		details.setUser(user);
		session.save(user);
		session.save(details);
		
		            <<<< with cascade >>>> 
		           
		User user = new User( "Ahmed", 22);
		userDetails details = new userDetails("Cairo", 123456789);
		details.setUser(user);
		session.persist(details);
		
		*/
		
/*   2- Add User with Friends */
		
		/*
		 * List<User> users = new ArrayList<>(); users.add(new User( "Ahmed", 22));
		 * users.add(new User("Mohamed", 25));
		 * 
		 * List<Frinds> frinds = new ArrayList<>(); frinds.add(new Frinds(null, "Ali"));
		 * frinds.add(new Frinds(null, "Omar"));
		 * 
		 * users.forEach(user -> session.save(user));
		 * frinds.forEach(f->f.setUser(users)); frinds.forEach(f->session.save(f));
		 * 
		 *        <<<< with cascade >>>> 
		 *
		 *List<User> users = new ArrayList<>(); 
		  users.add(new User( "Ahmed", 22));
		  users.add(new User("Mohamed", 25));
		  
		  List<Frinds> frinds = new ArrayList<>(); 
		  frinds.add(new Frinds("Ali"));
		  frinds.add(new Frinds("Omar"));
		  frinds.forEach(f->f.setUser(users)); 
		  frinds.forEach(f->session.persist(f));
		 */
	
		
/*   3- Add User with Posts */
		/*
		 * List<Post> posts = new ArrayList<>();
		 * 
		 * posts.add(new Post( "Java", "Learn Java")); posts.add(new Post( "Hibernate",
		 * "Learn Hibernate")); posts.add(new Post("Spring Boot", "Learn Spring Boot"));
		 * 
		 * User user = new User("Ahmed", 22);
		 * 
		 * session.save(user); posts.forEach(post -> post.setUser(user));
		 * posts.forEach(post -> session.save(post));
		 * 
		 *       <<<< with cascade >>>> 
		 *       
		 *List<Post> posts = new ArrayList<>();
		 
		 posts.add(new Post( "Java", "Learn Java")); 
		 posts.add(new Post( "Hibernate","Learn Hibernate"));
		 posts.add(new Post("Spring Boot", "Learn Spring Boot"));
		 
		 User user = new User("Ahmed", 22);
		 session.save(user);
		 posts.forEach(post -> post.setUser(user));
		 posts.forEach(post -> session.persist(post));
		 
		 */
		
//	                	<<<< Using Fetch Eager >>>> 
		
		/*
		 * userDetails details = session.get(userDetails.class, 2L);
		 * System.out.println(details);
		 */
	
//		               <<<< Using Fetch Lazy >>>> 
		/*
		 * userDetails details = session.get(userDetails.class, 2L);
		 * System.out.println(details); System.out.println(details.getUser().getName());
		 */
		
//                   <<<< Inheritance has been Applied. >>>> 
		
		transaction.commit();
		System.out.println("END PROGRAM");
		factory.close();
		session.close();

	}

}
