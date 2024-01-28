package com.example.instargram_clone.entity.follow.service;

import com.example.instargram_clone.entity.follow.dto.request.FollowDoRequest;
import com.example.instargram_clone.entity.follow.dto.request.FollowGetCountInfoRequest;
import com.example.instargram_clone.entity.follow.dto.request.FollowUndoRequest;
import com.example.instargram_clone.entity.follow.dto.response.FollowResponse;
import com.example.instargram_clone.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void follow(FollowDoRequest followDoRequest) {
        followRepository.save(followDoRequest.toEntity());
    }

    @Transactional
    public void unFollow(FollowUndoRequest followUndoRequest) {
        followRepository.unFollowMember(followUndoRequest.getFollower(), followUndoRequest.getFollowing());
    }

    @Transactional
    public FollowResponse getFollowingCount(FollowGetCountInfoRequest followGetFollowingRequest) {
        return FollowResponse.from(followRepository.countFollowingsByFollowId(followGetFollowingRequest.getMemberId()));
    }

    @Transactional
    public FollowResponse getFollowerCount(FollowGetCountInfoRequest followGetFollowingRequest) {
        return FollowResponse.from(followRepository.countFollowersByFollowId(followGetFollowingRequest.getMemberId()));
    }
}
