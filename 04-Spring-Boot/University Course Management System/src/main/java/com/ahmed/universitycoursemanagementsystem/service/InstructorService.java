package com.ahmed.universitycoursemanagementsystem.service;

import com.ahmed.universitycoursemanagementsystem.model.Course;
import com.ahmed.universitycoursemanagementsystem.model.Instructor;

import java.util.List;

public interface InstructorService {
    Instructor createInstructor(Instructor instructor);

    List<Instructor> getAllInstructors();

    List<Course> getCoursesTaughtByInstructor(Long instructorId);
}
