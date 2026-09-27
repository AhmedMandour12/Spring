package com.ahmed.dto_demo_task.service.impl;

import com.ahmed.dto_demo_task.exception.ResourceNotFoundException;
import com.ahmed.dto_demo_task.mapper.TeacherMapper;
import com.ahmed.dto_demo_task.model.Teacher;
import com.ahmed.dto_demo_task.model.dto.TeacherDTO.TeacherResponse;
import com.ahmed.dto_demo_task.repository.TeacherRepo;
import com.ahmed.dto_demo_task.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

   private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper;

    @Override
    public List<TeacherResponse> getAllTeachers() {
        return teacherRepo.findAll()
                .stream()
                .map(teacherMapper::toResponse)
                .toList();
    }


    @Override
    public TeacherResponse getTeacherById(Long teacherId) {
       return teacherRepo.findByIdWithStudents(teacherId)
                .map(teacherMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

    }
}
