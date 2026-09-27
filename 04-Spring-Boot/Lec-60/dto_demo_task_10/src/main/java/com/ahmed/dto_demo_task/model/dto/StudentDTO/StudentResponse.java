package com.ahmed.dto_demo_task.model.dto.StudentDTO;

import com.ahmed.dto_demo_task.model.Student;
import com.ahmed.dto_demo_task.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long id;

    private String name;

    private String grade;
}
