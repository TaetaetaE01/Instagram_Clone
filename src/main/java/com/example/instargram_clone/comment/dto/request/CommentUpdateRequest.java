package com.example.instargram_clone.comment.dto.request;

import com.example.instargram_clone.member.domain.Member;
import com.example.instargram_clone.post.domain.Post;
import lombok.Data;

@Data
public class CommentUpdateRequest {
    private Long id;
    private String content;
    private Member member;
    private Post post;
}
