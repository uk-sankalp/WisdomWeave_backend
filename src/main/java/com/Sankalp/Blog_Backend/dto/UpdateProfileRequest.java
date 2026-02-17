package com.Sankalp.Blog_Backend.dto;

import jakarta.validation.constraints.Size;

public class UpdateProfileRequest {
    @Size(min = 3, max = 100)
    private String username;
    @Size(max = 500)
    private String bio;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}

