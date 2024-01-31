package com.example.instargram_clone.domain.member.dto.request;

import com.example.instargram_clone.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    @NotNull(message = "이메일을 입력해주세요")
    private String email;
    @NotNull(message = "이름을 입력해주세요")
    private String name;
    @NotNull(message = "비밀번호를 입력해주세요")
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
