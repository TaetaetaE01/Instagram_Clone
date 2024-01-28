package com.example.instargram_clone.entity.follow.service;

import com.example.instargram_clone.entity.follow.dto.request.FollowDoRequest;
import com.example.instargram_clone.entity.follow.dto.request.FollowGetFollowerInfoCountRequest;
import com.example.instargram_clone.entity.follow.dto.request.FollowGetFollowingCountRequest;
import com.example.instargram_clone.entity.follow.dto.request.FollowUndoRequest;
import com.example.instargram_clone.entity.follow.dto.response.FollowResponse;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.member.service.MemberService;
import com.example.instargram_clone.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberService memberService;

    @Transactional
    public void follow(FollowDoRequest followDoRequest) {
        Member follower = memberService.getMemberInfoExist(followDoRequest.getFollower());
        Member following = memberService.getMemberInfoExist(followDoRequest.getFollowing());
        followRepository.save(followDoRequest.toEntity(follower, following));
    }

    @Transactional
    public void unFollow(FollowUndoRequest followUndoRequest) {
        followRepository.deleteByFollowerIdAndFollowingId(followUndoRequest.getFollowerId(), followUndoRequest.getFollowingId());
    }

    @Transactional
    public FollowResponse getFollowingCount(FollowGetFollowingCountRequest followGetFollowingCountRequest) {
        return FollowResponse.from(followRepository.countByFollowerId(followGetFollowingCountRequest.getFollowerId()));
    }

    @Transactional
    public FollowResponse getFollowerCount(FollowGetFollowerInfoCountRequest followGetFollowerInfoCountRequest) {
        return FollowResponse.from(followRepository.countByFollowingId(followGetFollowerInfoCountRequest.getFollowingId()));
    }
}
