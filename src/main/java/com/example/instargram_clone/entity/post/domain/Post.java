package com.example.instargram_clone.entity.post.domain;

import com.example.instargram_clone.entity.comment.domain.Comment;
import com.example.instargram_clone.entity.like.domain.LikeEntity;
import com.example.instargram_clone.entity.member.domain.Member;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String posturl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // Comment 리스트 oneToMany
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Comment> commentSet;


    @Builder
    public Post(String content, Member member, String posturl, Set<Comment> commentSet, Set<LikeEntity> likeSet) {
        this.content = content;
        this.posturl = posturl;
        this.member = member;
        this.commentSet = commentSet;
//        this.likeSet = likeSet;
    }

    public void update(String content, Member member, String posturl) {
        this.content = content;
        this.posturl = posturl;
        this.member = member;
    }
}
