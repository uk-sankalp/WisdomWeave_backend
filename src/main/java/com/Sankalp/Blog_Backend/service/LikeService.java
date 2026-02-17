package com.Sankalp.Blog_Backend.service;

import com.Sankalp.Blog_Backend.entity.Like;
import com.Sankalp.Blog_Backend.entity.Post;
import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.repo.LikeRepo;
import com.Sankalp.Blog_Backend.repo.PostRepo;
import com.Sankalp.Blog_Backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final LikeRepo likeRepo;

    public void like(Long postId, String email){
        User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found"));
        Post post=postRepo.findById(postId).orElseThrow(()->new RuntimeException("Post Not Found"));

        if(likeRepo.existsByPostIdAndUserId(postId, user.getId())){
            return;
        }
        likeRepo.save(
            Like.builder()
                    .post(post)
                    .user(user)
                    .build()
        );
    }
    public long count(Long postId){
        return likeRepo.countByPostId(postId);
    }

    public void unlike(Long postId, String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        likeRepo.findByPostIdAndUserId(postId, user.getId())
                .ifPresent(likeRepo::delete);
    }

}
