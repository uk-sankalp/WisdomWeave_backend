package com.Sankalp.Blog_Backend.repo;

import com.Sankalp.Blog_Backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
}
