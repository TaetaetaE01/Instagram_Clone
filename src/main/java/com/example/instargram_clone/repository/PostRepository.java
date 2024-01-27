package com.example.instargram_clone.repository;

import com.example.instargram_clone.entity.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByMemberId(Long memberId);
}
