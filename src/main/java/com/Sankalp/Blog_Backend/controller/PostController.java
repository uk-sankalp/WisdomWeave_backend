package com.Sankalp.Blog_Backend.controller;

import com.Sankalp.Blog_Backend.dto.CreatePostRequest;
import com.Sankalp.Blog_Backend.dto.PostResponse;
import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public PostResponse create(
            @Valid @RequestBody CreatePostRequest request,
            Authentication auth){
        User user = (User) auth.getPrincipal();
    return postService.createPost(request,user.getEmail());
    }

    @GetMapping("/{id}")
    public PostResponse getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @GetMapping
    public Page<PostResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return postService.list(page,size);
    }

    @GetMapping("/search")
    public Page<PostResponse> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return postService.search(keyword,page,size);
    }
}
