package com.Sankalp.Blog_Backend.service;

import com.Sankalp.Blog_Backend.dto.CommentResponse;
import com.Sankalp.Blog_Backend.dto.CreateCommentRequest;
import com.Sankalp.Blog_Backend.entity.Comment;
import com.Sankalp.Blog_Backend.entity.Post;
import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.repo.CommentRepo;
import com.Sankalp.Blog_Backend.repo.PostRepo;
import com.Sankalp.Blog_Backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;

    public CommentResponse add(Long id,CreateCommentRequest request,String email){
    Post post=postRepo.findById(id).orElseThrow(()->new RuntimeException("Post Not Found"));
    User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found"));
        Comment comment=Comment.builder()
                .comment(request.getComment())
                .post(post)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        commentRepo.save(comment);
        return map(comment);
    }
    public List<CommentResponse> list(Long postId){
        return commentRepo.findByPostId(postId)
                .stream()
                .map(this::map)
                .toList();
    }

    private CommentResponse map(Comment c) {
        return CommentResponse.builder()
                .id(c.getId())
                .comment(c.getComment())
                .author(c.getUser().getUsername())
                .createdAt(c.getCreatedAt())
                .build();
    }
}
