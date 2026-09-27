package com.ahmed.task_11.service;



import com.ahmed.task_11.model.dto.PostDto;
import com.ahmed.task_11.model.dto.PostWithUserDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto dto);

    PostDto getPostById(Long id);

    List<PostDto> getAllPosts();

    PostDto updatePost(Long id, PostDto dto);

    void deletePost(Long id);

    List<PostDto> getPostsByUserId(Long userId);

    List<PostWithUserDto> getAllPostsWithUsers();

    PostWithUserDto getPostWithUser(Long id);
}
