package com.ahmed.universitycoursemanagementsystem.repository;

import com.ahmed.universitycoursemanagementsystem.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepo extends JpaRepository<Instructor,Long> {
}
