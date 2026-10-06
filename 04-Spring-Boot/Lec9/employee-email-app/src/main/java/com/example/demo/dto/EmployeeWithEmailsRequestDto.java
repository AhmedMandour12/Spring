package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeWithEmailsRequestDto extends EmployeeRequestDto {

    @NotEmpty(message = "emails list must not be empty")
    private List<@Valid EmailRequestDto> emails;
}
