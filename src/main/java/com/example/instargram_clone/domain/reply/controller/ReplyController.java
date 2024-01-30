package com.example.instargram_clone.domain.reply.controller;

import com.example.instargram_clone.domain.reply.dto.request.ReplyGetRepliesListRequest;
import com.example.instargram_clone.domain.reply.dto.request.ReplyRegisterRequest;
import com.example.instargram_clone.domain.reply.dto.request.ReplyUpdateRequest;
import com.example.instargram_clone.domain.reply.dto.response.ReplyResponse;
import com.example.instargram_clone.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/instagram/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<Void> registerReply(@RequestBody ReplyRegisterRequest replyRegisterRequest) {
        replyService.registerReply(replyRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable("id") Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateReply(@RequestBody ReplyUpdateRequest replyUpdateRequest) {
        replyService.updateReply(replyUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponse> getOneReply(@PathVariable("id") Long id) {
        ReplyResponse replyResponse = replyService.findOne(id);
        return ResponseEntity.ok().body(replyResponse);
    }

    @GetMapping("/getReplies")
    public ResponseEntity<List<ReplyResponse>> getReplies(@RequestBody ReplyGetRepliesListRequest replyGetRepliesListRequest) {
        List<ReplyResponse> repliesList = replyService.getRepliesListByCommentId(replyGetRepliesListRequest);
        return ResponseEntity.ok(repliesList);
    }
}
