package com.example.instargram_clone.domain.reply.dto.request;

import com.example.instargram_clone.domain.comment.entity.Comment;
import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.post.entity.Post;
import com.example.instargram_clone.domain.reply.entity.Reply;

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
