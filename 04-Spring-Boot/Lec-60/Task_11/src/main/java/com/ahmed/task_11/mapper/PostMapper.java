package com.ahmed.task_11.mapper;

import com.ahmed.task_11.model.Post;
import com.ahmed.task_11.model.User;
import com.ahmed.task_11.model.dto.PostDto;
import com.ahmed.task_11.model.dto.PostSummaryDto;
import com.ahmed.task_11.model.dto.PostWithUserDto;
import com.ahmed.task_11.model.dto.UserSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "userId", source = "user.id")
    PostDto toDto(Post post);

    PostSummaryDto toSummaryDto(Post post);

    List<PostSummaryDto> toSummaryDtoList(List<Post> posts);

    @Mapping(target = "user", ignore = true)
    Post toEntity(PostDto dto);

    @Mapping(target = "user", source = "user")
    PostWithUserDto toWithUserDto(Post post);

    UserSummaryDto toUserSummaryDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDto(PostDto dto, @MappingTarget Post post);
}