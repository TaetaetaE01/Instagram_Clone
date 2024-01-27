package com.example.instargram_clone.repository;

import com.example.instargram_clone.entity.comment.domain.Comment;
import com.example.instargram_clone.entity.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Long countLikesByPostId(Long postId);
}
