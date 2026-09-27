package com.ahmed.task_11.controller;
import com.ahmed.task_11.model.dto.PostDto;
import com.ahmed.task_11.model.dto.PostWithUserDto;
import com.ahmed.task_11.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @Valid @RequestBody PostDto dto) {
        return ResponseEntity.ok(postService.updatePost(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/postsWithUsers")
    public ResponseEntity<List<PostWithUserDto>> getAllPostsWithUsers() {
        return ResponseEntity.ok(postService.getAllPostsWithUsers());
    }

    @GetMapping("/postWithUsers/{id}")
    public ResponseEntity<PostWithUserDto> getPostWithUser(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostWithUser(id));
    }
}
