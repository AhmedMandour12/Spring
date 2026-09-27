package com.ahmed.task_11.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Long id;

    @NotNull(message = "Text is required")
    @Size(min = 20, message = "Text must be at least 20 characters long")
    private String text;

    private String imagePath;

    private Long userId;
}
