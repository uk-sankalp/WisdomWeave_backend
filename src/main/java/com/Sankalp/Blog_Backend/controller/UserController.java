package com.Sankalp.Blog_Backend.controller;

import com.Sankalp.Blog_Backend.dto.UpdateProfileRequest;
import com.Sankalp.Blog_Backend.dto.UserProfileResponse;
import com.Sankalp.Blog_Backend.dto.UserResponse;
import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse me(Authentication auth){
        User user=(User) auth.getPrincipal();
        return userService.getById(user.getId());
    }

    @GetMapping("/me/profile")
    public UserProfileResponse profile(Authentication auth) {

        User user = (User) auth.getPrincipal();
        return userService.getProfile(user.getId());
    }


    @PatchMapping("/me/profile")
    public UserResponse updateProfile(Authentication auth, @RequestBody UpdateProfileRequest request){
        User user=(User) auth.getPrincipal();
        return userService.updateProfile(user.getId(),request);
    }

    @PostMapping("/me/avatar")
    public UserResponse uploadAvatar(Authentication auth,@RequestParam("file") MultipartFile image){
        User user = (User) auth.getPrincipal();
        return userService.uploadAvatar(user.getId(),image);
    }

    @DeleteMapping("/me/avatar")
    public UserResponse deleteAvatar(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return userService.deleteAvatar(user.getId());
    }

}
