package com.example.instargram_clone.post.controller;

import com.example.instargram_clone.post.dto.request.*;
import com.example.instargram_clone.post.dto.response.PostResponse;
import com.example.instargram_clone.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instagram/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> registerPost(@RequestBody PostCreateRequest postCreateRequest) {
        postService.registerPost(postCreateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostInfo(@PathVariable("id") Long id) {
        postService.deletePostInfo(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePostInfo(@RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatePostInfo(postUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostOneInfo(@PathVariable("id") Long id) {
        PostResponse postResponse = postService.findOne(id);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/findAll")
    public ResponseEntity<PostResponse> findAll() {
        List<PostResponse> postResponseList = postService.findAll();
        return ResponseEntity.ok((PostResponse) postResponseList);
    }




}
