package com.example.instargram_clone.entity.post.dto.request;


import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.post.domain.Post;
import lombok.Data;

@Data
public class PostCreateRequest {

    private String content;
    private Long member;
    private String posturl;

    public Post toEntity(Member member) {
        return Post.builder()
                .content(content)
                .posturl(posturl)
                .member(member)
                .build();
    }
}
