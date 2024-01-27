package com.example.instargram_clone.entity.reply.controller;

import com.example.instargram_clone.entity.comment.dto.request.CommentRegisterRequest;
import com.example.instargram_clone.entity.comment.dto.request.CommentUpdateRequest;
import com.example.instargram_clone.entity.comment.dto.response.CommentResponse;
import com.example.instargram_clone.entity.reply.dto.request.ReplyRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/instagram/replies")
@RequiredArgsConstructor
public class ReplyController {

//    private ReplyService replyService;
    @PostMapping
    public ResponseEntity<Void> registerComment(@RequestBody ReplyRegisterRequest replyRegisterRequest) {
//        replyService.registerPost(replyRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
//        replyService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
//        replyService.updateComment(commentUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getOneComment(@PathVariable("id") Long id) {
//        CommentResponse commentResponse = replyService.findOne(id);
        return ResponseEntity.ok().build();
    }
}
