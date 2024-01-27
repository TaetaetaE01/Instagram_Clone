package com.example.instargram_clone.entity.member.dto.request;

import com.example.instargram_clone.entity.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateRequest {

    private String email;
    private String name;
    private String password;
    private String profileurl;
    private String statusMessage;

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
