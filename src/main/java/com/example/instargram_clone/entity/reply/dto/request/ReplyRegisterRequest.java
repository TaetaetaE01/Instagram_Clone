package com.example.instargram_clone.entity.reply.dto.request;

import com.example.instargram_clone.entity.comment.domain.Comment;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.post.domain.Post;
import com.example.instargram_clone.entity.reply.domain.Reply;

import lombok.Data;

@Data
public class ReplyRegisterRequest {
    private String content;
    private Long member;
    private Long post;
    private Long comment;

    public Reply toEntity(Member member, Post post, Comment comment) {
        return Reply.builder()
                .content(content)
                .member(member)
                .post(post)
                .comment(comment)
                .build();
    }
}
