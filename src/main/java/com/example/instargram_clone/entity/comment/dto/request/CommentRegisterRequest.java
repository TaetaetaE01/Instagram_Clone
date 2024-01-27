package com.example.instargram_clone.entity.comment.dto.request;

import com.example.instargram_clone.entity.comment.domain.Comment;

import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.post.domain.Post;
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
