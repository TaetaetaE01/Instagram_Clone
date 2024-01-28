package com.example.instargram_clone.entity.follow.controller;

import com.example.instargram_clone.entity.follow.dto.request.FollowDoRequest;
import com.example.instargram_clone.entity.follow.dto.request.FollowGetCountInfoRequest;
import com.example.instargram_clone.entity.follow.dto.request.FollowUndoRequest;
import com.example.instargram_clone.entity.follow.dto.response.FollowResponse;
import com.example.instargram_clone.entity.follow.service.FollowService;
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

    @DeleteMapping("/unFollow")
    public ResponseEntity<Void> unFollow(@RequestBody FollowUndoRequest followUndoRequest) {
        followService.unFollow(followUndoRequest);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getFollowing")
    public ResponseEntity<FollowResponse> getFollowingCountByFollowId(@RequestBody FollowGetCountInfoRequest followGetFollowingRequest) {
        FollowResponse followResponse = followService.getFollowingCount(followGetFollowingRequest);
        return ResponseEntity.ok().body(followResponse);
    }

    @GetMapping("/getFollower")
    public ResponseEntity<FollowResponse> getFollowerCountByFollowingId(@RequestBody FollowGetCountInfoRequest followGetFollowingRequest) {
        FollowResponse followResponse = followService.getFollowerCount(followGetFollowingRequest);
        return ResponseEntity.ok().body(followResponse);
    }


}
