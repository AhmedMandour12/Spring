package com.ahmed.dto_demo_task.service.impl;

import com.ahmed.dto_demo_task.exception.ResourceNotFoundException;
import com.ahmed.dto_demo_task.mapper.StudentMapper;
import com.ahmed.dto_demo_task.model.dto.StudentDTO.StudentResponse;
import com.ahmed.dto_demo_task.repository.StudentRepo;
import com.ahmed.dto_demo_task.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepo.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        return studentRepo.findByIdWithTeachers(studentId)
                .map(studentMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
    }
}