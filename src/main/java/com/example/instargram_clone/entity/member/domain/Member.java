package com.example.instargram_clone.entity.member.domain;


import com.example.instargram_clone.entity.follow.domain.Follow;
import com.example.instargram_clone.entity.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Post> postSet;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.REMOVE)
    private Set<Follow> followers;

    @OneToMany(mappedBy = "following", cascade = CascadeType.REMOVE)
    private Set<Follow> following;

    @Builder
    public Member(String email, String name, String password, String profileurl, String statusMessage, Set<Post> postSet, Set<Follow> followers, Set<Follow> following) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileurl = profileurl;
        this.statusMessage = statusMessage;
        this.postSet = postSet;
        this.followers = followers;
        this.following = following;
    }


    public void update(String email, String name, String password, String profileurl, String statusMessage) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileurl = profileurl;
        this.statusMessage = statusMessage;
    }
}
