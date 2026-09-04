package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity // model-1
public class Student extends Person{
	private Integer age;

	@ManyToMany
	@JoinTable(name = "Student_Course", joinColumns = @JoinColumn(name = "student_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "course_id"))
	List<Course> courses;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Student() {
		super();
	}

	public Student(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
		this.courses = courses;
	}

}
