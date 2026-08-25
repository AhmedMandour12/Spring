package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Team;

public class Main {

	public static void main(String[] args) {

		// Load hibernate.cfg.xml and register the Team entity mapping
		Configuration configuration = new Configuration().addAnnotatedClass(Team.class).configure("hibernate.cfg.xml");

		/*
		 * 1-buildSessionFactory 2-getCurrentSession print session
		 *
		 */

		// Build the SessionFactory (heavyweight object, create once per app)
		SessionFactory factory = configuration.buildSessionFactory();

		// Get the current session bound to this thread
		Session session = factory.getCurrentSession();

		// Start a new transaction before doing any database work
		Transaction transaction = session.beginTransaction();

		// Create a new Team object (in-memory, not yet saved)
		Team team1 = new Team("Real Madrid", 1L, 20, true);

		Team team2 = new Team("Barcelona", 2L, 19, true);
		// get object via cons(id)

//	Team team2 =session.getReference(Team.class, 2L);

		// Tell Hibernate to save this entity (queued, not sent to DB yet)
		session.update(team1);
		session.update(team2);

		// Commit the transaction -> flushes the save and executes the INSERT,
		// then auto-closes the session since it came from getCurrentSession()
		transaction.commit();

		// Note: session is already closed here after commit,
		// so this just prints the object reference, not live session data
		System.out.println(session);
		session.close();
		factory.close();
	}
}