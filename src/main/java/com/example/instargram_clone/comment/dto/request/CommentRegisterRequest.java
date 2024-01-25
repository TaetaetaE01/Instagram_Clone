package com.example.instargram_clone.comment.dto.request;

import com.example.instargram_clone.comment.domain.Comment;
import com.example.instargram_clone.member.domain.Member;
import com.example.instargram_clone.post.domain.Post;
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
