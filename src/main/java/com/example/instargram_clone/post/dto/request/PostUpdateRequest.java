package com.example.instargram_clone.post.dto.request;


import com.example.instargram_clone.member.domain.Member;
import lombok.Data;
import lombok.Getter;

@Data
public class PostUpdateRequest {
    private Long id;
    private String content;
    private Long member;
    private String posturl;
}
