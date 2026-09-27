package com.ahmed.task_11.controller;

import com.ahmed.task_11.model.dto.PostDto;
import com.ahmed.task_11.model.dto.UserDto;
import com.ahmed.task_11.model.dto.UserWithPostsDto;
import com.ahmed.task_11.service.PostService;
import com.ahmed.task_11.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostsByUserId(id));
    }

    @GetMapping("/usersWithPost")
    public ResponseEntity<List<UserWithPostsDto>> getAllUsersWithPosts() {
        return ResponseEntity.ok(userService.getAllUsersWithPosts());
    }

    @GetMapping("/userWithPost/{id}")
    public ResponseEntity<UserWithPostsDto> getUserWithPosts(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserWithPosts(id));
    }
}
