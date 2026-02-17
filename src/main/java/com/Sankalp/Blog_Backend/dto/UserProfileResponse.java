package com.Sankalp.Blog_Backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserProfileResponse {

    private Long id;
    private String username;
    private String email;
    private String bio;
    @JsonProperty("avatarUrl")
    private String avatarUrl;
    private String role;
    private LocalDateTime createdAt;

    public UserProfileResponse(Long id, String username, String email,
                               String bio, String avatarUrl,
                               String role, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.role = role;
        this.createdAt = createdAt;
    }

    // getters only (immutable DTO style)
}

