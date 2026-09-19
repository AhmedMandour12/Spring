package com.ahmed.universitycoursemanagementsystem.service;

import com.ahmed.universitycoursemanagementsystem.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long StudentId);

    Student registerStudentToCourse(Long studentId, Long courseId);
}
