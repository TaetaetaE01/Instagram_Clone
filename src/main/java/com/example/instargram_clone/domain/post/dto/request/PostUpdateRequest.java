package com.example.instargram_clone.domain.post.dto.request;


import lombok.Data;

@Data
public class PostUpdateRequest {
    private Long id;
    private String content;
    private Long member;
    private String posturl;

    public PostUpdateRequest(Long id, String content, Long member) {
        this.id = id;
        this.content = content;
        this.member = member;
    }
}
