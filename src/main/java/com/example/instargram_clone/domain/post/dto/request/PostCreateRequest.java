package com.example.instargram_clone.domain.post.dto.request;


import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.post.entity.Post;
import lombok.Data;

@Data
public class PostCreateRequest {

    private String content;
    private Long member;
    private String posturl;

    public PostCreateRequest(String content, Long member){
        this.content = content;
        this.member = member;
    }

    public Post toEntity(Member member) {
        return Post.builder()
                .content(content)
                .posturl(posturl)
                .member(member)
                .build();
    }
}
