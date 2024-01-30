package com.example.instargram_clone.domain.reply.dto.response;

import com.example.instargram_clone.domain.reply.entity.Reply;
import lombok.Data;

@Data
public class ReplyResponse {
    private Long id;
    private Long member;
    private Long post;
    private Long comment;
    private String content;

    public ReplyResponse(Long id, Long member, Long post, Long comment, String content) {
        this.id = id;
        this.member = member;
        this.post = post;
        this.comment = comment;
        this.content = content;
    }

    public static ReplyResponse from(Reply reply) {
        return new ReplyResponse(reply.getId(), reply.getMember().getId(), reply.getPost().getId(), reply.getComment().getId(), reply.getContent());
    }
}
