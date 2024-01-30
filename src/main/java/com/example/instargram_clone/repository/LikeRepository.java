package com.example.instargram_clone.repository;

import com.example.instargram_clone.domain.like.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Long countLikesByPostId(Long postId);
}
