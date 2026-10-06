package com.example.demo.repository;

import com.example.demo.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByName(String name);

    List<Email> findByNameIn(List<String> names);

    List<Email> findByContent(String content);
}
