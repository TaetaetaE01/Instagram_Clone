package com.example.instargram_clone.comment.cotroller;

import com.example.instargram_clone.comment.dto.request.CommentRegisterRequest;
import com.example.instargram_clone.comment.dto.request.CommentUpdateRequest;
import com.example.instargram_clone.comment.service.CommentService;
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(commentUpdateRequest);
        return ResponseEntity.ok().build();
    }

}
