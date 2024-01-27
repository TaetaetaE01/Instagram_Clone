package com.example.instargram_clone.entity.post.dto.response;

import com.example.instargram_clone.entity.post.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse {
    private Long id;
    private String content;
    private String posturl;
    private Long memberId;


    public PostResponse(Long id, String content, String posturl, Long memberId) {
        this.id = id;
        this.content = content;
        this.posturl = posturl;
        this.memberId = memberId;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getContent(), post.getPosturl(), post.getMember().getId());
    }
}
