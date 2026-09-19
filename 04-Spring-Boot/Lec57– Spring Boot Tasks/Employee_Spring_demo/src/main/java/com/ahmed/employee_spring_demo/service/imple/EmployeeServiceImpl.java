package com.ahmed.employee_spring_demo.service.imple;

import com.ahmed.employee_spring_demo.ExceptionHandler.EmployeeNotFoundException;
import com.ahmed.employee_spring_demo.model.Employee;
import com.ahmed.employee_spring_demo.repository.EmployeeRepository;
import com.ahmed.employee_spring_demo.service.EemployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EemployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByIds(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public Employee updateEmployee( Long id,Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> updateEmployees(List<Employee> employees) {

        for (Employee employee : employees) {
            Long id=employee.getId();
            if(!employeeRepository.existsById(id)){
                throw new EmployeeNotFoundException(id);
            }

        }
        return employeeRepository.saveAll(employees);
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();

    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public void deleteEmployeesByIds(List<Long> ids) {
        employeeRepository.deleteAllById(ids);

    }

    @Override
    public List<Employee> searchEmployeeByName(String name) {
        return employeeRepository.searchEmployeeByName(name);
    }

}
