package com.example.instargram_clone.domain.reply.dto.request;

import lombok.Data;

@Data
public class ReplyUpdateRequest {
    private Long id;
    private String content;
    private Long member;
    private Long post;
    private Long Comment;
}
