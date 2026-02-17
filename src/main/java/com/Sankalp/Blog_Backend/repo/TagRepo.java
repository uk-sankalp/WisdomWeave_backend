package com.Sankalp.Blog_Backend.repo;

import com.Sankalp.Blog_Backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName(String name);
}
