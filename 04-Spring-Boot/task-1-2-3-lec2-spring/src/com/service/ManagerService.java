package com.service;

public class ManagerService implements UserService{
	  @Override
	    public void save(String name) {
	        System.out.println("Manager saved: " + name);
	    }

	    @Override
	    public void update(String name) {
	        System.out.println("Manager updated: " + name);
	    }
}
