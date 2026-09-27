package com.ahmed.task_11.service;

import com.ahmed.task_11.model.dto.UserDto;
import com.ahmed.task_11.model.dto.UserWithPostsDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto dto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto dto);

    void deleteUser(Long id);

    UserWithPostsDto getUserWithPosts(Long id);

    List<UserWithPostsDto> getAllUsersWithPosts();
}
