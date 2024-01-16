package com.example.instargram_clone.member.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    private String profileurl;

    private String statusMessage;

    @Builder
    public Member(String email, String name, String password, String profileurl, String statusMessage) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileurl = profileurl;
        this.statusMessage = statusMessage;
    }


    public void update(String email, String name, String password, String profileurl, String statusMessage) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileurl = profileurl;
        this.statusMessage = statusMessage;
    }
}
