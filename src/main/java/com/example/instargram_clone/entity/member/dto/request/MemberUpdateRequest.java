package com.example.instargram_clone.entity.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateRequest {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String profileurl;
    private String statusMessage;
}
