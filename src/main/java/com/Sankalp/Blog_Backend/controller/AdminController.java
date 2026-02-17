package com.Sankalp.Blog_Backend.controller;

import com.Sankalp.Blog_Backend.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AnalyticsService analyticsService;
    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Map<String,Object> dashboard(){
        return analyticsService.dashboard();
    }
}
