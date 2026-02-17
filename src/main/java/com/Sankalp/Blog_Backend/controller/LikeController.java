package com.Sankalp.Blog_Backend.controller;

import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public void like(@PathVariable Long postId, Authentication auth){
        User user=(User) auth.getPrincipal();
        likeService.like(postId, user.getEmail());
    }

    @GetMapping
    public long count(@PathVariable Long postId){
        return likeService.count(postId);
    }

    @DeleteMapping
    public void unlike(@PathVariable Long postId, Authentication auth) {
        User user=(User) auth.getPrincipal();
        likeService.unlike(postId, user.getEmail());
    }

}
