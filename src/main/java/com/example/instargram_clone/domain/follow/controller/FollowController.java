package com.example.instargram_clone.domain.follow.controller;

import com.example.instargram_clone.domain.follow.dto.request.FollowDoRequest;
import com.example.instargram_clone.domain.follow.dto.request.FollowGetFollowerInfoCountRequest;
import com.example.instargram_clone.domain.follow.dto.request.FollowGetFollowingCountRequest;
import com.example.instargram_clone.domain.follow.dto.request.FollowUndoRequest;
import com.example.instargram_clone.domain.follow.dto.response.FollowResponse;
import com.example.instargram_clone.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/instagram/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<Void> follow(@RequestBody FollowDoRequest followDoRequest) {
        followService.follow(followDoRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unFollow(@RequestBody FollowUndoRequest followUndoRequest) {
        followService.unFollow(followUndoRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getFollowing")
    public ResponseEntity<FollowResponse> getFollowingCountByFollowId(@RequestBody FollowGetFollowingCountRequest followGetFollowingRequest) {
        FollowResponse followResponse = followService.getFollowingCount(followGetFollowingRequest);
        return ResponseEntity.ok().body(followResponse);
    }

    @GetMapping("/getFollower")
    public ResponseEntity<FollowResponse> getFollowerCountByFollowingId(@RequestBody FollowGetFollowerInfoCountRequest followGetFollowerInfoCountRequest) {
        FollowResponse followResponse = followService.getFollowerCount(followGetFollowerInfoCountRequest);
        return ResponseEntity.ok().body(followResponse);
    }


}
