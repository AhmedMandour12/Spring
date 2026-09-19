package com.ahmed.employee_spring_demo.repository;

import com.ahmed.employee_spring_demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

//  @Query(value = "SELECT * FROM employee WHERE name LIKE %:name%", nativeQuery = true) -->Native Query
//  @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%") --> Non-Native Query / JPQL
  List<Employee>searchEmployeeByName( /*@Param("name")*/ String name);
}
