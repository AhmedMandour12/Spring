package com.ahmed.universitycoursemanagementsystem.service;

import com.ahmed.universitycoursemanagementsystem.model.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);

    List<Course> getAllCourses();

    Course assignInstructorToCourse(Long courseId, Long instructorId);
}
