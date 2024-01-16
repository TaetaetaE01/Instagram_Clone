package com.example.instargram_clone.repository;

import com.example.instargram_clone.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
