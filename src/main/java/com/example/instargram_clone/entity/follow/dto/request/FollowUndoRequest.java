package com.example.instargram_clone.entity.follow.dto.request;


import com.example.instargram_clone.entity.member.domain.Member;
import lombok.Getter;

@Getter
public class FollowUndoRequest {
    private Member follower;
    private Member following;
}
