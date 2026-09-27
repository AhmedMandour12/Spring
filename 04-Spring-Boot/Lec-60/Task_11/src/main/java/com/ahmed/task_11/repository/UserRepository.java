package com.ahmed.task_11.repository;


import com.ahmed.task_11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
