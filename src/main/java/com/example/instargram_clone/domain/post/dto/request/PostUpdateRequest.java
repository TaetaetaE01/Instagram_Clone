package com.example.instargram_clone.domain.post.dto.request;


import lombok.Data;

@Data
public class PostUpdateRequest {
    private Long id;
    private String content;
    private Long member;
    private String posturl;
}
