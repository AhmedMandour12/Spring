package com.ahmed.task_11.service.empl;

import com.ahmed.task_11.exception.ResourceNotFoundException;
import com.ahmed.task_11.mapper.PostMapper;
import com.ahmed.task_11.model.Post;
import com.ahmed.task_11.model.User;
import com.ahmed.task_11.model.dto.PostDto;

import com.ahmed.task_11.model.dto.PostWithUserDto;
import com.ahmed.task_11.repository.PostRepository;
import com.ahmed.task_11.repository.UserRepository;
import com.ahmed.task_11.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    public PostDto createPost(PostDto dto) {
        Post post = postMapper.toEntity(dto);
        attachUserIfPresent(post, dto.getUserId());
        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public PostDto getPostById(Long id) {
        return postMapper.toDto(findPostOrThrow(id));
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public PostDto updatePost(Long id, PostDto dto) {
        Post post = findPostOrThrow(id);
        postMapper.updateEntityFromDto(dto, post);
        attachUserIfPresent(post, dto.getUserId());
        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.delete(findPostOrThrow(id));
    }

    @Override
    public List<PostDto> getPostsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return postRepository.findByUserId(userId).stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public List<PostWithUserDto> getAllPostsWithUsers() {
        return postRepository.findAll().stream()
                .map(postMapper::toWithUserDto)
                .toList();
    }

    @Override
    public PostWithUserDto getPostWithUser(Long id) {
        return postMapper.toWithUserDto(findPostOrThrow(id));
    }

    private void attachUserIfPresent(Post post, Long userId) {
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
            post.setUser(user);
        }
    }

    private Post findPostOrThrow(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }
}
