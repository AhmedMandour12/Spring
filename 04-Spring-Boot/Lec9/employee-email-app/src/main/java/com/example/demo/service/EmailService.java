package com.example.demo.service;

import com.example.demo.dto.EmailRequestDto;
import com.example.demo.dto.EmailResponseDto;
import com.example.demo.entity.Email;
import com.example.demo.entity.Employee;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.EmailMapper;
import com.example.demo.repository.EmailRepository;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailMapper emailMapper;

    public EmailResponseDto create(EmailRequestDto dto) {
        if (dto.getEmployeeId() == null) {
            throw new BadRequestException("employeeId is required to create an email");
        }
        Email email = emailMapper.toEntity(dto);
        email.setEmployee(findEmployeeOrThrow(dto.getEmployeeId()));
        return emailMapper.toDto(emailRepository.save(email));
    }

    public EmailResponseDto update(Long id, EmailRequestDto dto) {
        Email email = findOrThrow(id);
        emailMapper.updateEntity(dto, email);
        if (dto.getEmployeeId() != null) {
            email.setEmployee(findEmployeeOrThrow(dto.getEmployeeId()));
        }
        return emailMapper.toDto(emailRepository.save(email));
    }

    public void delete(Long id) {
        Email email = findOrThrow(id);
        // keep the parent's collection in sync (orphanRemoval)
        email.getEmployee().getEmails().remove(email);
        emailRepository.delete(email);
    }

    @Transactional(readOnly = true)
    public List<EmailResponseDto> getAll() {
        return emailMapper.toDtoList(emailRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<EmailResponseDto> getByName(String name) {
        return emailMapper.toDtoList(emailRepository.findByName(name));
    }

    @Transactional(readOnly = true)
    public List<EmailResponseDto> getByNames(List<String> names) {
        return emailMapper.toDtoList(emailRepository.findByNameIn(names));
    }

    @Transactional(readOnly = true)
    public List<EmailResponseDto> getByContent(String content) {
        return emailMapper.toDtoList(emailRepository.findByContent(content));
    }

    private Email findOrThrow(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found with id " + id));
    }

    private Employee findEmployeeOrThrow(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
    }
}
