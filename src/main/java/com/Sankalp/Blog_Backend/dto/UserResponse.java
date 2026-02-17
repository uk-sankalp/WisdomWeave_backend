package com.Sankalp.Blog_Backend.dto;

import com.Sankalp.Blog_Backend.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String avatarUrl;
    private Role role;
}
