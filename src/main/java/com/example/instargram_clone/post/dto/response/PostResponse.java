package com.example.instargram_clone.post.dto.response;

import com.example.instargram_clone.post.domain.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponse {
    private Long id;
    private String content;
    private Long memberId;


    private String memberName;
//    private List<Reply> replyList;

    public PostResponse(Long id, String content, Long memberId, String memberName) {
        this.id = id;

        this.content = content;
        this.memberId = memberId;
        this.memberName = memberName;
//        this.replyList = replyList;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getContent(), post.getMember().getId(), post.getMember().getName());
    }
}
