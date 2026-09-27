package com.ahmed.dto_demo_task.controller;

import com.ahmed.dto_demo_task.model.dto.TeacherDTO.TeacherResponse;
import com.ahmed.dto_demo_task.service.impl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
   private final  TeacherServiceImpl teacherService;

   @GetMapping
    public List<TeacherResponse> getAllTeachers() {
       return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}")
    public TeacherResponse getTeacherById(@PathVariable Long teacherId) {
        return teacherService.getTeacherById(teacherId);

    }
}
