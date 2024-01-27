package com.example.instargram_clone.entity.reply.dto.response;

import com.example.instargram_clone.entity.comment.domain.Comment;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.post.domain.Post;
import com.example.instargram_clone.entity.reply.domain.Reply;
import lombok.Data;

@Data
public class ReplyResponse {
    private Long id;
    private Member member;
    private Post post;
    private Comment comment;
    private String content;

    public ReplyResponse(Long id, Member member, Post post, Comment comment, String content) {
        this.id = id;
        this.member = member;
        this.post = post;
        this.comment = comment;
        this.content = content;
    }

    public static ReplyResponse from(Reply reply) {
        return new ReplyResponse(reply.getId(), reply.getMember(), reply.getPost(), reply.getComment(), reply.getContent());
    }
}
