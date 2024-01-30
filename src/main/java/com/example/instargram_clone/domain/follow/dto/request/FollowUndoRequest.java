package com.example.instargram_clone.domain.follow.dto.request;


import lombok.Getter;

@Getter
public class FollowUndoRequest {
    private Long followerId;
    private Long followingId;
}
