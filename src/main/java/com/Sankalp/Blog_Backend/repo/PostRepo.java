package com.Sankalp.Blog_Backend.repo;

import com.Sankalp.Blog_Backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {

    Page<Post> findByPublishedTrue(Pageable pageable);
    Page<Post> findByTitleContainingIgnoreCase(String keyword,Pageable pageable);
}
