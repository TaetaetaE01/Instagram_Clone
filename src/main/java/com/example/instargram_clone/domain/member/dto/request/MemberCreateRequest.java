package com.example.instargram_clone.domain.member.dto.request;

import com.example.instargram_clone.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    private String email;
    private String name;
    private String password;
    private String profileurl;
    private String statusMessage;

    public MemberCreateRequest(String mail, String testUser, String password) {
        this.email = mail;
        this.name = testUser;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .profileurl(profileurl)
                .statusMessage(statusMessage)
                .build();
    }
}
