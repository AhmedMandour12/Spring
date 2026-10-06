package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {

    @NotBlank(message = "name must not be null or empty")
    private String name;

    @NotNull(message = "age is required")
    @Min(value = 16, message = "age must be greater than 15")
    @Max(value = 39, message = "age must be less than 40")
    private Integer age;

    @NotNull(message = "salary is required")
    @DecimalMin(value = "5000", inclusive = false, message = "salary must be greater than 5000")
    @DecimalMax(value = "10000", inclusive = false, message = "salary must be less than 10000")
    private BigDecimal salary;
}
