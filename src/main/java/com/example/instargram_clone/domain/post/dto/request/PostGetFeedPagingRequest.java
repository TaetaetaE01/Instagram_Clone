package com.example.instargram_clone.domain.post.dto.request;

import lombok.Getter;

@Getter
public class PostGetFeedPagingRequest {
    private Long member;

    public PostGetFeedPagingRequest(Long member) {
        this.member = member;
    }
}
