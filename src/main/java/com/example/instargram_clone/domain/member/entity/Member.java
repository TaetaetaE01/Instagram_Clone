package com.example.instargram_clone.domain.member.entity;


import com.example.instargram_clone.domain.follow.entity.Follow;
import com.example.instargram_clone.domain.like.entity.LikeEntity;
import com.example.instargram_clone.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<LikeEntity> likeSet;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.REMOVE)
    private Set<Follow> followers;

    @OneToMany(mappedBy = "following", cascade = CascadeType.REMOVE)
    private Set<Follow> following;

    @Builder
    public Member(Long id, String email, String name, String password, String profileurl, String statusMessage, Set<Post> postSet, Set<Follow> followers, Set<Follow> following, Set<LikeEntity> likeSet) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileurl = profileurl;
        this.statusMessage = statusMessage;
        this.postSet = postSet;
        this.followers = followers;
        this.following = following;
        this.likeSet = likeSet;
    }


    public void update(String email, String name, String password, String profileurl, String statusMessage) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileurl = profileurl;
        this.statusMessage = statusMessage;
    }

    public void encodePassword(BCryptPasswordEncoder bCryptPasswordEncoder) {
        bCryptPasswordEncoder.encode(this.password);
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

}
