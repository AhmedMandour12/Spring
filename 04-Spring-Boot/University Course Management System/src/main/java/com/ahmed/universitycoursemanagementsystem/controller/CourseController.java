package com.ahmed.universitycoursemanagementsystem.controller;

import com.ahmed.universitycoursemanagementsystem.model.Course;
import com.ahmed.universitycoursemanagementsystem.service.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

   private  CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return  courseService.createCourse(course);
    }
    @PostMapping("/assign/{courseId}/{instructorId}")
    public Course assignInstructorToCourse(@PathVariable Long courseId,@PathVariable Long instructorId){
        return courseService.assignInstructorToCourse(courseId,instructorId);
    }
    @GetMapping("/all")
    List<Course> getAllCourses(){
      return   courseService.getAllCourses();
    }
}
