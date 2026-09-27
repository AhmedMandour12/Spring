package com.ahmed.dto_demo_task.repository;

import com.ahmed.dto_demo_task.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.students WHERE t.id = :id")
    Optional<Teacher> findByIdWithStudents(@Param("id") Long id);

}
