package com.example.instargram_clone.domain.post.dto.request;

import lombok.Getter;

@Getter
public class PostGetPostsListRequest {
    private Long member;

    public PostGetPostsListRequest(Long member) {
        this.member = member;
    }
}
