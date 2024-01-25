package com.example.instargram_clone.post.domain;

import com.example.instargram_clone.comment.domain.Comment;
import com.example.instargram_clone.member.domain.Member;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(String content, Member member, String posturl, List<Comment> commentList) {
        this.content = content;
        this.posturl = posturl;
        this.member = member;
        this.commentList = commentList;
    }


    public void update(String content, Member member, String posturl) {
        this.content = content;
        this.posturl = posturl;
        this.member = member;
    }
}
