package com.example.instargram_clone.post.dto.request;


import com.example.instargram_clone.member.domain.Member;
import lombok.Getter;

@Getter
public class PostUpdateRequest {
    private Long id;
    private String title;
    private String content;
    private Member member;
    private String posturl;
}
