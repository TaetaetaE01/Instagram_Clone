package com.example.instargram_clone.domain.member.dto.response;

import com.example.instargram_clone.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;

    private String email;

    private String name;

    private String password;

    private String profileurl;

    private String statusMessage;

    public MemberResponse(Long id, String email, String name, String password, String profileurl, String statusMessage) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileurl = profileurl;
        this.statusMessage = statusMessage;
    }


    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getName(), member.getPassword(), member.getProfileurl(), member.getStatusMessage());
    }
}
