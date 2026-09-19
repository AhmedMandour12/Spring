package com.ahmed.employee_spring_demo.ExceptionHandler;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee not exist with id: " + id);
    }
}
