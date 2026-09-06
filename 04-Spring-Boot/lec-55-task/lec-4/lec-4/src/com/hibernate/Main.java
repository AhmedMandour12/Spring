package com.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Player;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration()
		.addAnnotatedClass(Player.class).configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		
		 Player player1 = new Player(1L, "Ahmed", 9,
		  true); session.save(player1); Player player2 = session.get(Player.class, 1L);
		  System.out.println(player2);
		 
		
		transaction.commit();
		System.out.println("END PROGRAM");
		factory.close();
		session.close();

	}

}
