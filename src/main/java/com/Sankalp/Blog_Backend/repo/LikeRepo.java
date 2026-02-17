package com.Sankalp.Blog_Backend.repo;

import com.Sankalp.Blog_Backend.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepo extends JpaRepository<Like,Long> {
    boolean existsByPostIdAndUserId(Long postId,Long userId);
    long countByPostId(Long postId);
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);
}
