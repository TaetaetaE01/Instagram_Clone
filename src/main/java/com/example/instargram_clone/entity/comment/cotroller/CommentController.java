package com.example.instargram_clone.entity.comment.cotroller;

import com.example.instargram_clone.entity.comment.dto.request.CommentRegisterRequest;
import com.example.instargram_clone.entity.comment.dto.request.CommentUpdateRequest;
import com.example.instargram_clone.entity.comment.dto.response.CommentResponse;
import com.example.instargram_clone.entity.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/instagram/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> registerComment(@RequestBody CommentRegisterRequest commentRegisterRequest) {
        commentService.registerPost(commentRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(commentUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getOneComment(@PathVariable("id") Long id) {
        CommentResponse commentResponse = commentService.findOne(id);
        return ResponseEntity.ok(commentResponse);
    }
}
