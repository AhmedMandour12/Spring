package com.ahmed.universitycoursemanagementsystem.repository;

import com.ahmed.universitycoursemanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {
}
