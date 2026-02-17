package com.Sankalp.Blog_Backend.controller;

import com.Sankalp.Blog_Backend.dto.LoginRequest;
import com.Sankalp.Blog_Backend.dto.RegisterRequest;
import com.Sankalp.Blog_Backend.dto.UserResponse;
import com.Sankalp.Blog_Backend.service.AuthService;
import com.Sankalp.Blog_Backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
    return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        String token=authService.login(request);
        return ResponseEntity.ok(Map.of("token",token));
    }

}
