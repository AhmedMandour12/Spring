package com.ahmed.task_11.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithPostsDto {

    private Long id;

    private String name;

    private Integer age;

    private List<PostSummaryDto> posts;
}
