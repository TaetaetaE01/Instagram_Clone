package com.example.instargram_clone.domain.comment.dto.request;

import com.example.instargram_clone.domain.comment.entity.Comment;

import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.post.entity.Post;
import lombok.Data;


@Data
public class CommentRegisterRequest {
    private String content;
    private Long member;
    private Long post;

    public Comment toEntity(Member member, Post post) {
        return Comment.builder()
                .content(content)
                .member(member)
                .post(post)
                .build();
    }
}
