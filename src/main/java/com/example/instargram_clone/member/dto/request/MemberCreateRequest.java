package com.example.instargram_clone.member.dto.request;

import com.example.instargram_clone.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberCreateRequest {

    private String email;
    private String name;
    private String pw;
    private String profileURL;
    private String statusMessage;
    private LocalDateTime createTime;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .name(this.name)
                .pw(this.pw)
                .profileURL(this.profileURL)
                .statusMessage(this.statusMessage)
                .build();
    }
}
