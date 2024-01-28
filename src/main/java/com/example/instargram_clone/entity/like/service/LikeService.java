package com.example.instargram_clone.entity.like.service;

import com.example.instargram_clone.entity.like.domain.Like;
import com.example.instargram_clone.entity.like.dto.request.LikeCreateRequest;
import com.example.instargram_clone.entity.like.dto.request.LikeGetLikeCountRequest;
import com.example.instargram_clone.entity.like.dto.response.LikeResponse;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.member.service.MemberService;
import com.example.instargram_clone.entity.post.domain.Post;
import com.example.instargram_clone.entity.post.service.PostService;
import com.example.instargram_clone.repository.LikeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.example.instargram_clone.config.BaseResponseStatus.GET_LIKES_NOT_EXISTS_INFO;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    public void doLike(LikeCreateRequest likeCreateRequest) {
        Member member = memberService.getMemberInfoExist(likeCreateRequest.getMember());
        Post post = postService.getPostInfoExist(likeCreateRequest.getPost());
        likeRepository.save(likeCreateRequest.toEntity(member, post));
    }

    @Transactional
    public void undoLike(Long id) {
        likeRepository.delete(getLikeInfoExit(id));
    }

    @Transactional(readOnly = true)
    public Like getLikeInfoExit(Long id) {
        Like like = likeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(GET_LIKES_NOT_EXISTS_INFO.getMessage()));
        return like;
    }

    @Transactional
    public LikeResponse getLikeCount(LikeGetLikeCountRequest likegetLikeCountRequest) {
        return LikeResponse.from(likeRepository.countLikesByPostId(likegetLikeCountRequest.getPost()));
    }
}
