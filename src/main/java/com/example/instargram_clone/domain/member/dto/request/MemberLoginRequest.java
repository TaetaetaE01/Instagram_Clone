package com.example.instargram_clone.domain.member.dto.request;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
    private String email;
    private String pw;
}
