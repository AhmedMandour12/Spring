package com.ahmed.task_11.service.empl;

import com.ahmed.task_11.exception.ResourceNotFoundException;
import com.ahmed.task_11.mapper.UserMapper;
import com.ahmed.task_11.model.User;
import com.ahmed.task_11.model.dto.UserDto;
import com.ahmed.task_11.model.dto.UserWithPostsDto;
import com.ahmed.task_11.repository.UserRepository;
import com.ahmed.task_11.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto dto) {
        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.toDto(findUserOrThrow(id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto updateUser(Long id, UserDto dto) {
        User user = findUserOrThrow(id);
        userMapper.updateEntityFromDto(dto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(findUserOrThrow(id));
    }

    @Override
    public UserWithPostsDto getUserWithPosts(Long id) {
        return userMapper.toWithPostsDto(findUserOrThrow(id));
    }

    @Override
    public List<UserWithPostsDto> getAllUsersWithPosts() {
        return userRepository.findAll().stream()
                .map(userMapper::toWithPostsDto)
                .toList();
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}
