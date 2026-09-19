package com.ahmed.universitycoursemanagementsystem.service.impl;

import com.ahmed.universitycoursemanagementsystem.model.Course;
import com.ahmed.universitycoursemanagementsystem.repository.CourseRepo;
import com.ahmed.universitycoursemanagementsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepo courseRepo ;
    @Autowired
    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public Course createCourse(Course course) {

        courseRepo.save(course);
        return course;
    }

    @Override
    public List<Course> getAllCourses() {

        return List.of();
    }

    @Override
    public Course assignInstructorToCourse(Long courseId, Long instructorId) {
        return null;
    }
}
