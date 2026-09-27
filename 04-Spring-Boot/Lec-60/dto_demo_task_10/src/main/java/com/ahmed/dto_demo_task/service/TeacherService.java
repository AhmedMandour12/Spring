package com.ahmed.dto_demo_task.service;

import com.ahmed.dto_demo_task.model.dto.TeacherDTO.TeacherResponse;

import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAllTeachers();
    TeacherResponse getTeacherById(Long teacherId);

}
