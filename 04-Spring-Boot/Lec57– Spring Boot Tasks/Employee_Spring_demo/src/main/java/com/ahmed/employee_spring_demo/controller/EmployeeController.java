package com.ahmed.employee_spring_demo.controller;
import com.ahmed.employee_spring_demo.model.Employee;
import com.ahmed.employee_spring_demo.service.imple.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    List<Employee> getAllEmployees(){
       return employeeService.getAllEmployees();
    }
    @GetMapping("by/ids")
    List<Employee> getEmployeesByIds(@RequestParam List<Long> ids){
        return  employeeService.getEmployeesByIds(ids);
    }
    @PostMapping
    Employee saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;

    }
    @PostMapping("saveEmployees")
    List<Employee> saveEmployees(@RequestBody List<Employee> employees){
        employeeService.saveEmployees(employees);
        return employees;
    }
    @PostMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return employee;
    }

    @PostMapping("/updateEmployees")
    List<Employee> updateEmployees(@RequestBody List<Employee> employees){
        employeeService.updateEmployees(employees);
        return employees;
    }
    @PostMapping("/deleteAll")
    void deleteAllEmployees(){
        employeeService.deleteAllEmployees();
    }
    @PostMapping("/deleteById/{id}")
    void deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);

    }
    @PostMapping("/deleteAllByIDS")
    void deleteEmployeesByIds(@RequestParam List<Long> ids){
        employeeService.deleteEmployeesByIds(ids);
    }
    @GetMapping("/Search")
    List<Employee> searchEmployeeByName(@RequestParam String name){
        return employeeService.searchEmployeeByName(name);

    }

}
