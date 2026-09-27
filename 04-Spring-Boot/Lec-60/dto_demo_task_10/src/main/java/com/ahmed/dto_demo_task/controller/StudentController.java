package com.ahmed.dto_demo_task.controller;

import com.ahmed.dto_demo_task.model.dto.StudentDTO.StudentResponse;
import com.ahmed.dto_demo_task.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

     private final StudentServiceImpl studentService;

    @GetMapping
    public List<StudentResponse> getAllStudents(){

        return studentService.getAllStudents();
    }
    @GetMapping("/{studentId}")
    public StudentResponse getStudentById( @PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }
}
