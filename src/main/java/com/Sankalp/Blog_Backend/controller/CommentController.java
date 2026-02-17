package com.Sankalp.Blog_Backend.controller;

import com.Sankalp.Blog_Backend.dto.CommentResponse;
import com.Sankalp.Blog_Backend.dto.CreateCommentRequest;
import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponse add(@PathVariable Long postId, @Valid @RequestBody CreateCommentRequest request, Authentication auth){
        User user = (User) auth.getPrincipal();
        return commentService.add(postId,request,user.getEmail());
    }

    @GetMapping
    public List<CommentResponse> list(@PathVariable Long postId){
    return commentService.list(postId);
    }

}
