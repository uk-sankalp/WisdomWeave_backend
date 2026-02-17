package com.Sankalp.Blog_Backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private boolean published;
}

