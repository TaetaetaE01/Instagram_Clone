package com.example.instargram_clone.domain.follow.service;

import com.example.instargram_clone.config.BaseException;
import com.example.instargram_clone.domain.follow.dto.request.FollowDoRequest;
import com.example.instargram_clone.domain.follow.dto.request.FollowGetFollowerInfoCountRequest;
import com.example.instargram_clone.domain.follow.dto.request.FollowGetFollowingCountRequest;
import com.example.instargram_clone.domain.follow.dto.request.FollowUndoRequest;
import com.example.instargram_clone.domain.follow.dto.response.FollowResponse;
import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.member.service.MemberService;
import com.example.instargram_clone.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.instargram_clone.config.BaseResponseStatus.POST_FOLLOW_INFO_EXISTS;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberService memberService;

    @Transactional
    public void follow(FollowDoRequest followDoRequest) {
        Member follower = memberService.getMemberInfoExist(followDoRequest.getFollower());
        Member following = memberService.getMemberInfoExist(followDoRequest.getFollowing());

        if (followRepository.existsByFollowerIdAndFollowingId(followDoRequest.getFollower(), followDoRequest.getFollowing())) {
            throw new BaseException(POST_FOLLOW_INFO_EXISTS.getMessage());
        }

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

    @Transactional
    public List<Long> getFollowInfo(Long followerId) {
        return followRepository.findFollowingIdsByFollowerId(followerId);
    }

}
