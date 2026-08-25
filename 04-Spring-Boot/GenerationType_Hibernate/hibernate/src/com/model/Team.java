package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Check;

@Entity
public class Team {
	
	@Column(length = (50), nullable = false,unique = (true))
	private String name;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;

	@Check(constraints = ("age BETWEEN 15 AND 20"))
	private int playerCount;

	private boolean active;

	public Team(Long id) {
		this.id = id;
	}

	public Team(String name, Long id, int playerCount, boolean active) {
		super();
		this.name = name;
		this.id = id;
		this.playerCount = playerCount;
		this.active = active;
	}

	public Team() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
