package com.Sankalp.Blog_Backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentResponse {
    private Long id;
    private String comment;
    private String author;
    private LocalDateTime createdAt;
}
