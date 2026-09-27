package com.ahmed.task_11.mapper;

import com.ahmed.task_11.model.Post;
import com.ahmed.task_11.model.User;
import com.ahmed.task_11.model.dto.PostSummaryDto;
import com.ahmed.task_11.model.dto.UserDto;
import com.ahmed.task_11.model.dto.UserSummaryDto;
import com.ahmed.task_11.model.dto.UserWithPostsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "posts", ignore = true)
    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> users);

    UserSummaryDto toSummaryDto(User user);

    PostSummaryDto toPostSummaryDto(Post post);

    UserWithPostsDto toWithPostsDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    void updateEntityFromDto(UserDto dto, @MappingTarget User user);
}