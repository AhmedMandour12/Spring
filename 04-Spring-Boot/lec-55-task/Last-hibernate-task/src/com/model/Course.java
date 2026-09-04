package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity //model-2
public class Course extends Person{
	
	@ManyToMany(mappedBy = "courses",cascade = CascadeType.PERSIST)
	List<Student> students;
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Course( String name) {
		super();
		this.name = name;
		this.students = students;
	}
	public Course() {
		super();
	}
	

}
