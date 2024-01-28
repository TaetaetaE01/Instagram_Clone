package com.example.instargram_clone.entity.follow.dto.request;

import com.example.instargram_clone.entity.follow.domain.Follow;

import com.example.instargram_clone.entity.member.domain.Member;
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
