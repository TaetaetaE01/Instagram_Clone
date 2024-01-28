package com.example.instargram_clone.repository;

import com.example.instargram_clone.entity.follow.domain.Follow;
import com.example.instargram_clone.entity.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Modifying
    @Query("DELETE FROM Follow  WHERE follower = :follower AND following = :following")
    void unFollowMember(Member follower, Member following);

    @Query("SELECT COUNT(f.following) FROM Follow f WHERE f.follower = :followId")
    Long countFollowingsByFollowId(Long followId);

    @Query("SELECT COUNT(f.follower) FROM Follow f WHERE f.following = :followingId")
    Long countFollowersByFollowId(Long followingId);
}
