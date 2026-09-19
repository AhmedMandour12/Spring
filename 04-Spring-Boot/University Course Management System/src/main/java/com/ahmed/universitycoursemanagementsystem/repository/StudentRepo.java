package com.ahmed.universitycoursemanagementsystem.repository;

import com.ahmed.universitycoursemanagementsystem.model.Instructor;
import com.ahmed.universitycoursemanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
