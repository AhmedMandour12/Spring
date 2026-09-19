package com.ahmed.employee_spring_demo.service;

import com.ahmed.employee_spring_demo.model.Employee;

import java.util.List;

public interface EemployeeService {
    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByIds(List<Long> ids);

    Employee saveEmployee(Employee employee);

    List<Employee> saveEmployees(List<Employee> employees);

    Employee updateEmployee( Long id,Employee employee);

    List<Employee> updateEmployees(List<Employee> employees);

    void deleteAllEmployees();

    void deleteEmployeeById(Long id);

    void deleteEmployeesByIds(List<Long> ids);

    List<Employee> searchEmployeeByName(String name);
}
