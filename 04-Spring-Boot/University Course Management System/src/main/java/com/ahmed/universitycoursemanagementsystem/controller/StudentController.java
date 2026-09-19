package com.ahmed.universitycoursemanagementsystem.controller;

import com.ahmed.universitycoursemanagementsystem.model.Student;
import com.ahmed.universitycoursemanagementsystem.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
public class StudentController {

    StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
   public Student createStudent(@RequestBody Student student){
        studentService.createStudent(student);
        return student;

    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents(){
       return studentService.getAllStudents();

    }

    @GetMapping("/{StudentId}")
    public Student getStudentById(@PathVariable Long StudentId){
        return studentService.getStudentById(StudentId);

    }

    @PostMapping("/register/{studentId}/{courseId}")
    public Student registerStudentToCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        return studentService.registerStudentToCourse(studentId, courseId);
    }
}
