package com.ahmed.universitycoursemanagementsystem.service.impl;

import com.ahmed.universitycoursemanagementsystem.model.Course;
import com.ahmed.universitycoursemanagementsystem.model.Student;
import com.ahmed.universitycoursemanagementsystem.repository.CourseRepo;
import com.ahmed.universitycoursemanagementsystem.repository.StudentRepo;
import com.ahmed.universitycoursemanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepo studentRepo;
    CourseRepo courseRepo;
    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public Student createStudent(Student student) {


        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {

        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Long StudentId) {

        return studentRepo.getById(StudentId);
    }

    @Transactional
    @Override
    public Student registerStudentToCourse(Long studentId, Long courseId) {
      Student student= studentRepo.findById(studentId).orElseThrow(()-> new RuntimeException("Student not found with id: "+studentId));
      Course course = courseRepo.findById(courseId).orElseThrow(() ->new RuntimeException("Course not found with id: " + courseId));
        student.getCourses().add(course);
        return studentRepo.save(student);

    }
}
