package com.ahmed.dto_demo_task.mapper;

import com.ahmed.dto_demo_task.model.Student;
import com.ahmed.dto_demo_task.model.dto.StudentDTO.StudentRequest;
import com.ahmed.dto_demo_task.model.dto.StudentDTO.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "id" ,ignore = true)
    public Student toEntity(StudentRequest studentRequest);
    StudentResponse toResponse(Student student);


}
