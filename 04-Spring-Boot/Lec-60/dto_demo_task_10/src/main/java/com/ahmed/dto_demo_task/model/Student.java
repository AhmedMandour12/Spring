package com.ahmed.dto_demo_task.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "teachers")
@EqualsAndHashCode(exclude = "teachers")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String grade;

    @ManyToMany(mappedBy = "students",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("students")
    @Builder.Default
    private Set<Teacher> teachers = new HashSet<>();
}