package com.example.instargram_clone.entity.like.dto.request;


import com.example.instargram_clone.entity.like.domain.Like;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.post.domain.Post;
import lombok.Data;

@Data
public class LikeCreateRequest {
    private Long member;
    private Long post;

    public Like toEntity(Member member, Post post) {
        return Like.builder()
                .member(member)
                .post(post)
                .build();
    }
}
