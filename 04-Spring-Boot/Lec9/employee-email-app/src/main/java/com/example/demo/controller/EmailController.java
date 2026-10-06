package com.example.demo.controller;

import com.example.demo.dto.EmailRequestDto;
import com.example.demo.dto.EmailResponseDto;
import com.example.demo.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailResponseDto> create(@Valid @RequestBody EmailRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailResponseDto> update(@PathVariable Long id,
                                                   @Valid @RequestBody EmailRequestDto dto) {
        return ResponseEntity.ok(emailService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        emailService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmailResponseDto>> getAll() {
        return ResponseEntity.ok(emailService.getAll());
    }

    // GET /api/emails/by-name?name=gmail
    @GetMapping("/by-name")
    public ResponseEntity<List<EmailResponseDto>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(emailService.getByName(name));
    }

    // GET /api/emails/by-names?names=gmail,yahoo
    @GetMapping("/by-names")
    public ResponseEntity<List<EmailResponseDto>> getByNames(@RequestParam List<String> names) {
        return ResponseEntity.ok(emailService.getByNames(names));
    }

    // GET /api/emails/by-content?content=eslam@gmail.com
    @GetMapping("/by-content")
    public ResponseEntity<List<EmailResponseDto>> getByContent(@RequestParam String content) {
        return ResponseEntity.ok(emailService.getByContent(content));
    }
}
