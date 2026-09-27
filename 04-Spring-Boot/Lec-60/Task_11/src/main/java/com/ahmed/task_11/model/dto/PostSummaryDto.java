package com.ahmed.task_11.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSummaryDto {

    private Long id;

    private String text;

    private String imagePath;
}
