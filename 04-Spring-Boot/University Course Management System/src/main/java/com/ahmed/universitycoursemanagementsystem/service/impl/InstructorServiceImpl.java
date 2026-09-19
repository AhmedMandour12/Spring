package com.ahmed.universitycoursemanagementsystem.service.impl;

import com.ahmed.universitycoursemanagementsystem.model.Course;
import com.ahmed.universitycoursemanagementsystem.model.Instructor;
import com.ahmed.universitycoursemanagementsystem.repository.InstructorRepo;
import com.ahmed.universitycoursemanagementsystem.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepo instructorRepo;
    @Autowired
    public InstructorServiceImpl(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    @Override
    public Instructor createInstructor(Instructor instructor) {

        instructorRepo.save(instructor);
        return instructor;
    }

    @Override
    public List<Instructor> getAllInstructors() {

        return instructorRepo.findAll();
    }

    @Override
    public List<Course> getCoursesTaughtByInstructor(Long instructorId)
    {
       Instructor instructor= instructorRepo.findById(instructorId).orElseThrow(()->new RuntimeException("instructor not found id:"+instructorId));
        return new ArrayList<>(instructor.getCourses()) ;
    }
}
