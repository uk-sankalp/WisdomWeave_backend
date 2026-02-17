package com.Sankalp.Blog_Backend.service;

import com.Sankalp.Blog_Backend.dto.LoginRequest;
import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.repo.UserRepo;
import com.Sankalp.Blog_Backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(LoginRequest request){
        User user=userRepo.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("User Not Found"));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        return jwtUtil.generateToken(request.getEmail());
    }
}
