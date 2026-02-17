package com.Sankalp.Blog_Backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    @NotBlank
    private String comment;
}
