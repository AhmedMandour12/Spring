package com.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends BaseEntity {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
