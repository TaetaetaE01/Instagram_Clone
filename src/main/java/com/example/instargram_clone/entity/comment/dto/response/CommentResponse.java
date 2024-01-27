package com.example.instargram_clone.entity.comment.dto.response;

import com.example.instargram_clone.entity.comment.domain.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private Long member;
    private Long post;
    private String content;

    public CommentResponse(Long id, Long member, Long post, String content) {
        this.id = id;
        this.member = member;
        this.post = post;
        this.content = content;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getMember().getId(), comment.getPost().getId(), comment.getContent());
    }
}
