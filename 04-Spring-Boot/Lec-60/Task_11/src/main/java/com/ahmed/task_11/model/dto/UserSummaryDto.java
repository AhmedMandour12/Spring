package com.ahmed.task_11.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSummaryDto {

    private Long id;

    private String name;

    private Integer age;
}
