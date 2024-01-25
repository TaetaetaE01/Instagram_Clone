package com.example.instargram_clone.post.domain;

import com.example.instargram_clone.member.domain.Member;

import com.example.instargram_clone.post.dto.request.PostUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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
//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String content, Member member, String posturl) {
        this.content = content;
        this.posturl = posturl;
        this.member = member;
//        this.replyList = replyList;
    }


    public void update(String content, Member member, String posturl) {
        this.content = content;
        this.posturl = posturl;
        this.member = member;
    }
}
