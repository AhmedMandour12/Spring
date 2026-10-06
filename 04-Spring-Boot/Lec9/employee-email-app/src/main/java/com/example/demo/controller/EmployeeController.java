package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.EmployeeWithEmailsRequestDto;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(@Valid @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(dto));
    }

    /** Employee + list of emails (addresses), all saved together. */
    @PostMapping("/with-emails")
    public ResponseEntity<EmployeeResponseDto> createWithEmails(
            @Valid @RequestBody EmployeeWithEmailsRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createWithEmails(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    // GET /api/employees/by-ids?ids=1,2,3
    @GetMapping("/by-ids")
    public ResponseEntity<List<EmployeeResponseDto>> getByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(employeeService.getByIds(ids));
    }

    // GET /api/employees/by-names?names=Ali,Sara
    @GetMapping("/by-names")
    public ResponseEntity<List<EmployeeResponseDto>> getByNames(@RequestParam List<String> names) {
        return ResponseEntity.ok(employeeService.getByNames(names));
    }
}
