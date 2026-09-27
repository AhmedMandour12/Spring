package com.ahmed.dto_demo_task.model.dto.TeacherDTO;

import com.ahmed.dto_demo_task.model.Student;
import com.ahmed.dto_demo_task.model.dto.StudentDTO.StudentResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherResponse {
    private Long id;

    private String name;

    private String subject;
    List<StudentResponse> relatedStudents;

}
