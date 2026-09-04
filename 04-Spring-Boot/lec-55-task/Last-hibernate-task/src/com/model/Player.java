package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
@Entity
public class Player extends Person {

	@Max(10)
	private Integer age;
	private Boolean status;
	
	public Player(Long id) {
		this.id = id;
	}
	public Player() {
		
	}
	public Player(Long id, String name, @Max(10) Integer age, Boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", age=" + age + ", status=" + status + "]";
	}
	
	
	
	
	
	
}
