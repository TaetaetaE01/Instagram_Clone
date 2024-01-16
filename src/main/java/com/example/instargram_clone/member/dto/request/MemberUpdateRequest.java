package com.example.instargram_clone.member.dto.request;

import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String profileURL;
    private String statusMessage;
}
