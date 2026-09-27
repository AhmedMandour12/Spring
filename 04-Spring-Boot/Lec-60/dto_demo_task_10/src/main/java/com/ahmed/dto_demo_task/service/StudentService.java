package com.ahmed.dto_demo_task.service;

import com.ahmed.dto_demo_task.model.dto.StudentDTO.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long studentId);
}
