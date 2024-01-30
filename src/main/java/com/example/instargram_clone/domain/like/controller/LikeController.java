package com.example.instargram_clone.domain.like.controller;

import com.example.instargram_clone.domain.like.dto.request.LikeCreateRequest;
import com.example.instargram_clone.domain.like.dto.response.LikeResponse;
import com.example.instargram_clone.domain.like.service.LikeService;
import com.example.instargram_clone.domain.like.dto.request.LikeGetLikeCountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/instagram/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> doLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        likeService.doLike(likeCreateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> undoLike(@PathVariable("id") Long id) {
        likeService.undoLike(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getLikeCount")
    public ResponseEntity<LikeResponse> getLikeCountByPostId(@RequestBody LikeGetLikeCountRequest likegetLikeCountRequest) {
        LikeResponse likeResponse = likeService.getLikeCount(likegetLikeCountRequest);
        return ResponseEntity.ok().body(likeResponse);
    }
}
