package com.example.instargram_clone.domain.like.dto.response;

import lombok.Getter;

@Getter
public class LikeResponse {
    private Long likeCount;

    public LikeResponse(Long likeCount) {
        this.likeCount = likeCount;
    }

    public static LikeResponse from(Long likeCount) {
        return new LikeResponse(likeCount);
    }
}
