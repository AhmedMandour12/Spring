package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Frinds extends Person {


	public Frinds(String name) {
		this.name = name;
	}

	public Frinds() {

	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			joinColumns =@JoinColumn(name="frinds_user"),
			 inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	
	private List<User> user;

}
