package com.example.instargram_clone.domain.like.dto.request;


import com.example.instargram_clone.domain.like.entity.LikeEntity;
import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.post.entity.Post;
import lombok.Data;

@Data
public class LikeCreateRequest {
    private Long member;
    private Long post;

    public LikeEntity toEntity(Member member, Post post) {
        return LikeEntity.builder()
                .member(member)
                .post(post)
                .build();
    }
}
