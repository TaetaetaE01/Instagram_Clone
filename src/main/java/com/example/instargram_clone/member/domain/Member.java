package com.example.instargram_clone.member.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String pw;

    @Column(name = "profile_URL")
    private String profileURL;

    @Column(name = "status_Message")
    private String statusMessage;

    @Column(insertable = false, updatable = false, name = "create_date")
    private LocalDateTime createData;


    @Builder
    public Member(String email, String name, String pw, String profileURL, String statusMessage) {
        this.email = email;
        this.name = name;
        this.pw = pw;
        this.profileURL = profileURL;
        this.statusMessage = statusMessage;
    }

    public void update(String email, String name, String pw, String profileURL, String statusMessage) {
        this.email = email;
        this.name = name;
        this.pw = pw;
        this.profileURL = profileURL;
        this.statusMessage = statusMessage;
    }
}
