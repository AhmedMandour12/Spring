package com.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Users1")
public class User extends Person {

	private Integer age;

	public User( String name, Integer age) {
	
		this.age = age;
	}

	public User() {
		super();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public userDetails getDetails() {
		return details;
	}

	public void setDetails(userDetails details) {
		this.details = details;
	}

	public List<Frinds> getFrinds() {
		return frinds;
	}

	public void setFrinds(List<Frinds> frinds) {
		this.frinds = frinds;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@OneToOne(mappedBy = "user")
	private userDetails details;

	@ManyToMany(mappedBy = "user")
	private List<Frinds> frinds;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	

	

	

}
