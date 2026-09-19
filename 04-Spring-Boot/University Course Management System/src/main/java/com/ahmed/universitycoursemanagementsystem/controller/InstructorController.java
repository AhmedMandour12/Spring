package com.ahmed.universitycoursemanagementsystem.controller;

import com.ahmed.universitycoursemanagementsystem.model.Course;
import com.ahmed.universitycoursemanagementsystem.model.Instructor;
import com.ahmed.universitycoursemanagementsystem.service.impl.InstructorServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private InstructorServiceImpl instructorService;

    public InstructorController(InstructorServiceImpl instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor){
       return instructorService.createInstructor(instructor);
    }
    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{instructorId}/courses")
    public List<Course> getCoursesTaughtByInstructor(
            @PathVariable Long instructorId) {

        return instructorService.getCoursesTaughtByInstructor(instructorId);
    }


}
