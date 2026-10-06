package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto {

    @NotBlank(message = "name must not be null or empty")
    private String name;

    @NotBlank(message = "content must not be null or empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
             message = "content must be a valid email address")
    private String content;

    /**
     * Required when creating a standalone email (POST /api/emails).
     * Ignored when emails are nested inside an employee request.
     */
    private Long employeeId;
}
