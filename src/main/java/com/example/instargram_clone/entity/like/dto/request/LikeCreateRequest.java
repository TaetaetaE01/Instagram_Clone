package com.example.instargram_clone.entity.like.dto.request;


import com.example.instargram_clone.entity.like.domain.LikeEntity;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.post.domain.Post;
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
