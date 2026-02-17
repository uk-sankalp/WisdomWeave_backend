package com.Sankalp.Blog_Backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private boolean published;
    private String author;
    private LocalDateTime createdAt;
}

