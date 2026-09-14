package com.service;

public class PersonService implements UserService{

	  @Override
	    public void save(String name) {
	        System.out.println("Person saved: " + name);
	    }

	    @Override
	    public void update(String name) {
	        System.out.println("Person updated: " + name);
	    }

	    public void init() {
	        System.out.println("PersonService Bean Initialized");
	    }


	    public void destroy() {
	        System.out.println("PersonService Bean Destroyed");
	    }
}
