package com.example.instargram_clone.domain.follow.dto.response;

import lombok.Getter;

@Getter
public class FollowResponse {
    private Long followFlexibleCount;

    public FollowResponse(Long followFlexibleCount) {
        this.followFlexibleCount = followFlexibleCount;
    }

    public static FollowResponse from(Long followFlexibleCount) {
        return new FollowResponse(followFlexibleCount);
    }
}
