package com.example.instargram_clone.domain.follow.dto.request;

import com.example.instargram_clone.domain.follow.entity.Follow;

import com.example.instargram_clone.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class FollowDoRequest {
    // 팔로우 주는 사람
    private Long follower;
    // 팔로우 당하는 사람
    private Long following;

    public Follow toEntity(Member follower, Member following) {
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}
