package com.example.instargram_clone.domain.post.controller;

import com.example.instargram_clone.domain.post.dto.request.PostCreateRequest;
import com.example.instargram_clone.domain.post.dto.request.PostGetFeedPagingRequest;
import com.example.instargram_clone.domain.post.dto.request.PostGetPostsListRequest;
import com.example.instargram_clone.domain.post.dto.request.PostUpdateRequest;
import com.example.instargram_clone.domain.post.dto.response.PostResponse;
import com.example.instargram_clone.domain.post.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/instagram/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> registerPost(@RequestPart(value = "postCreateRequest") PostCreateRequest postCreateRequest,
                                             @RequestPart(value = "imageFile") List<MultipartFile> multipartFiles) throws IOException {
        postService.registerPost(postCreateRequest, multipartFiles);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostInfo(@PathVariable("id") Long id) {
        postService.deletePostInfo(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePostInfo(@RequestPart(value = "postUpdateRequest") PostUpdateRequest postUpdateRequest,
                                               @RequestPart(value = "imageFile") MultipartFile multipartFile) throws IOException {
        postService.updatePostInfo(postUpdateRequest, multipartFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostOneInfo(@PathVariable("id") Long id) {
        PostResponse postResponse = postService.findOne(id);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<PostResponse>> paging(@PageableDefault(page = 1) Pageable pageable) {
        Page<PostResponse> postsResponsePages = postService.paging(pageable);
        return ResponseEntity.ok(postsResponsePages);
    }

    @GetMapping("/getFeed")
    public ResponseEntity<Page<PostResponse>> getFeedPaging(@RequestBody PostGetFeedPagingRequest postGetFeedPagingRequest, @PageableDefault(page = 1) Pageable pageable) {
        Page<PostResponse> postsResponsePages = postService.feedPaging(postGetFeedPagingRequest, pageable);
        return ResponseEntity.ok(postsResponsePages);
    }

    @GetMapping("/getPostsByMember")
    public ResponseEntity<List<PostResponse>> getPostsByMember(@RequestBody PostGetPostsListRequest postGetPostsListRequest) {
        List<PostResponse> postList = postService.getPostListByMemberId(postGetPostsListRequest);
        return ResponseEntity.ok(postList);
    }
}
