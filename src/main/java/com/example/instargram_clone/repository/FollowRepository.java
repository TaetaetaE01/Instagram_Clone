package com.example.instargram_clone.repository;

import com.example.instargram_clone.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);

    Long countByFollowerId(Long followerId);

    Long countByFollowingId(Long followingId);

    List<Follow> findAllByFollowerId(Long followerId);

    boolean existsByFollowerIdAndFollowingId(Long followedId, Long followingId);
}
