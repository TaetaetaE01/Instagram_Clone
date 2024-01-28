package com.example.instargram_clone.entity.follow.domain;

import com.example.instargram_clone.entity.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private Member follower;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private Member following;

    @Builder
    public Follow(Long id, Member follower, Member following) {
        this.id = id;
        this.follower = follower;
        this.following = following;
    }
}
