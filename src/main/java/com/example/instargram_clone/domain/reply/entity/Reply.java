package com.example.instargram_clone.domain.reply.entity;

import com.example.instargram_clone.domain.comment.entity.Comment;
import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.post.entity.Post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public Reply(Long id, String content, Member member, Post post, Comment comment) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.post = post;
        this.comment = comment;
    }

    public void update(String content) {
        this.content = content;
    }
}
