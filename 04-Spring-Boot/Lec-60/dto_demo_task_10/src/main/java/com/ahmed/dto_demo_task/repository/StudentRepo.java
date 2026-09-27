package com.ahmed.dto_demo_task.repository;

import com.ahmed.dto_demo_task.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.teachers WHERE s.id = :id")
    Optional<Student> findByIdWithTeachers(@Param("id") Long id);
}
