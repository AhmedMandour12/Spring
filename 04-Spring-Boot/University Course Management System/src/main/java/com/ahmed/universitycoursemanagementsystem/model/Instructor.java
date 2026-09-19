package com.ahmed.universitycoursemanagementsystem.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    @NotBlank(message = "Name is requeried")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Email is required")
    @Email( message = "Email must be valid")
    private String email;

    @OneToMany(mappedBy = "instructor")
    Set<Course>courses=new HashSet<>();

}
