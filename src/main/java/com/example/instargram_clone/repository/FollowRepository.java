package com.example.instargram_clone.repository;

import com.example.instargram_clone.entity.follow.domain.Follow;
import com.example.instargram_clone.entity.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
//    @Modifying
//    @Query("DELETE FROM Follow  WHERE follower = :follower AND following = :following")
//    void unFollowMember(@Param("follower") Long follower, @Param("following") Long following);

    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);

    Long countByFollowerId(Long followerId);

    Long countByFollowingId(Long followingId);

    List<Long> findFollowingIdsByFollowerId(Long followerId);

//    @Query("SELECT COUNT(f.following) FROM Follow f WHERE f.follower = :memberFollowId")
//    Long countFollowingsByFollowId(@Param("memberFollowId") Long memberFollowId);
//
//    @Query("SELECT COUNT(f.follower) FROM Follow f WHERE f.following = :memberFollowId")
//    Long countFollowersByFollowId(@Param("memberFollowId")Long memberFollowId);
}
