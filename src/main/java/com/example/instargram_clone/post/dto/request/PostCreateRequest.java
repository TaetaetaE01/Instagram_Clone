package com.example.instargram_clone.post.dto.request;


import com.example.instargram_clone.member.domain.Member;
import com.example.instargram_clone.post.domain.Post;
import lombok.Getter;

@Getter
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
