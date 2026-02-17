package com.Sankalp.Blog_Backend.service;


import com.Sankalp.Blog_Backend.dto.RegisterRequest;
import com.Sankalp.Blog_Backend.dto.UpdateProfileRequest;
import com.Sankalp.Blog_Backend.dto.UserProfileResponse;
import com.Sankalp.Blog_Backend.dto.UserResponse;
import com.Sankalp.Blog_Backend.entity.Role;
import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.repo.UserRepo;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FileStorageService fileStorageService;

    public UserResponse register(RegisterRequest request){
    if(userRepo.existsByEmail(request.getEmail())){
        throw new RuntimeException("User Already Exists");
    }
        User user=User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode((request.getPassword())))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();
    userRepo.save(user);
    try {
        emailService.sendWelcomeEmail(user.getEmail());
    } catch (Exception e) {
        // Don't block registration if email fails (e.g. SMTP not configured)
    }
    return UserResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .avatarUrl(user.getAvatarUrl())
            .role(user.getRole())
            .build();
    }

    public UserResponse uploadAvatar(Long userId, MultipartFile image){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        if(user.getAvatarUrl()!=null){
            fileStorageService.delete(user.getAvatarUrl());
        }
        String path=fileStorageService.save(image);
        user.setAvatarUrl(path);
        userRepo.save(user);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole())
                .build();
    }
    public UserResponse deleteAvatar(Long userId){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        if (user.getAvatarUrl() != null) {
            fileStorageService.delete(user.getAvatarUrl());
            user.setAvatarUrl(null);
            userRepo.save(user);
        }
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(null)
                .role(user.getRole())
                .build();
    }

    public UserResponse updateProfile(Long userId, UpdateProfileRequest request){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        if(request.getUsername()!=null){
            user.setUsername(request.getUsername());
        }
        if(request.getBio()!=null){
            user.setBio(request.getBio());
        }
        userRepo.save(user);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole())
                .build();
    }
    public UserResponse getById(Long id){
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserProfileResponse getProfile(Long userId) {

        User user = userRepo.findById(userId).orElseThrow();

        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getAvatarUrl(),
                user.getRole().name(),
                user.getCreatedAt()
        );
    }

}
