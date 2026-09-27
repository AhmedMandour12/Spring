package com.ahmed.dto_demo_task.mapper;

import com.ahmed.dto_demo_task.model.Student;
import com.ahmed.dto_demo_task.model.Teacher;
import com.ahmed.dto_demo_task.model.dto.StudentDTO.StudentResponse;
import com.ahmed.dto_demo_task.model.dto.TeacherDTO.TeacherRequest;
import com.ahmed.dto_demo_task.model.dto.TeacherDTO.TeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(target = "id" ,ignore = true)
    public Teacher toEntity(TeacherRequest teacherRequest);
    @Mapping(source = "students", target = "relatedStudents")
    TeacherResponse toResponse(Teacher teacher);

    StudentResponse RESPONSE(Student student);
}
