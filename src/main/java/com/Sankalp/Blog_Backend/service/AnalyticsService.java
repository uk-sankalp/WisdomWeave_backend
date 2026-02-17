package com.Sankalp.Blog_Backend.service;

import com.Sankalp.Blog_Backend.repo.CommentRepo;
import com.Sankalp.Blog_Backend.repo.LikeRepo;
import com.Sankalp.Blog_Backend.repo.PostRepo;
import com.Sankalp.Blog_Backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final LikeRepo likeRepo;
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public Map<String, Object> dashboard(){
        long totalPosts=postRepo.count();
        long totalLikes=likeRepo.count();
        long totalUsers=userRepo.count();
        return Map.of(
                "posts", totalPosts,
                "users", totalUsers,
                "likes", totalLikes
        );
    }
}
