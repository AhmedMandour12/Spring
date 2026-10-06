package com.example.demo.service;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.EmployeeWithEmailsRequestDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponseDto create(EmployeeRequestDto dto) {
        Employee saved = employeeRepository.save(employeeMapper.toEntity(dto));
        return employeeMapper.toDto(saved);
    }

    /** Save an employee together with all of his emails in one transaction. */
    public EmployeeResponseDto createWithEmails(EmployeeWithEmailsRequestDto dto) {
        Employee employee = employeeMapper.toEntityWithEmails(dto);
        employee.getEmails().forEach(email -> email.setEmployee(employee));
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeResponseDto update(Long id, EmployeeRequestDto dto) {
        Employee employee = findOrThrow(id);
        employeeMapper.updateEntity(dto, employee);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void delete(Long id) {
        employeeRepository.delete(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getAll() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto getById(Long id) {
        return employeeMapper.toDto(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getByIds(List<Long> ids) {
        return employeeMapper.toDtoList(employeeRepository.findAllById(ids));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getByNames(List<String> names) {
        return employeeMapper.toDtoList(employeeRepository.findByNameIn(names));
    }

    private Employee findOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }
}
