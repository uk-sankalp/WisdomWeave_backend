package com.Sankalp.Blog_Backend.service;

import com.Sankalp.Blog_Backend.dto.CreatePostRequest;
import com.Sankalp.Blog_Backend.dto.PostResponse;
import com.Sankalp.Blog_Backend.entity.Post;
import com.Sankalp.Blog_Backend.entity.User;
import com.Sankalp.Blog_Backend.repo.PostRepo;
import com.Sankalp.Blog_Backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    @CacheEvict(value = "posts", allEntries = true)
    public PostResponse createPost(CreatePostRequest request,String email){
        User user=userRepo.findByEmail(email).orElseThrow(()-> new RuntimeException("User Not Found"));
        Post post=Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .published(request.isPublished())
                .author(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        postRepo.save(post);
        return map(post);
    }

    @Cacheable("posts")
    public Page<PostResponse> list(int page,int size){
        return postRepo.findByPublishedTrue(PageRequest.of(page,size))
                .map(this::map);
    }

    public Page<PostResponse> search(String keyword,int page,int size){
        return postRepo.findByTitleContainingIgnoreCase(keyword,PageRequest.of(page,size))
                .map(this::map);
    }

    public PostResponse getById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return map(post);
    }

    private PostResponse map(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .published(post.isPublished())
                .author(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
