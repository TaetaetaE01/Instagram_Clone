package com.example.instargram_clone.entity.comment.dto.request;

import lombok.Data;

@Data
public class CommentUpdateRequest {
    private Long id;
    private String content;
    private Long member;
    private Long post;
}
