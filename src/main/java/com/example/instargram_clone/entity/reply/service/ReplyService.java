package com.example.instargram_clone.entity.reply.service;

import com.example.instargram_clone.entity.comment.domain.Comment;
import com.example.instargram_clone.entity.comment.service.CommentService;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.member.service.MemberService;
import com.example.instargram_clone.entity.post.domain.Post;
import com.example.instargram_clone.entity.post.service.PostService;
import com.example.instargram_clone.entity.reply.domain.Reply;
import com.example.instargram_clone.entity.reply.dto.request.ReplyRegisterRequest;
import com.example.instargram_clone.entity.reply.dto.request.ReplyUpdateRequest;
import com.example.instargram_clone.entity.reply.dto.response.ReplyResponse;
import com.example.instargram_clone.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.example.instargram_clone.config.BaseResponseStatus.GET_REPLIES_NOT_EXISTS_INFO;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public void registerReply(ReplyRegisterRequest replyRegisterRequest) {
        Member member = memberService.getMemberInfoExist(replyRegisterRequest.getMember());
        Post post = postService.getPostInfoExist(replyRegisterRequest.getPost());
        Comment comment = commentService.getCommentInfoExist(replyRegisterRequest.getComment());

        replyRepository.save(replyRegisterRequest.toEntity(member, post, comment));
    }

    @Transactional
    public void deleteReply(Long id) {
        replyRepository.delete(getReplyInfoExist(id));
    }

    @Transactional
    public void updateReply(ReplyUpdateRequest replyUpdateRequest) {
        Reply reply = getReplyInfoExist(replyUpdateRequest.getId());
        reply.update(replyUpdateRequest.getContent());
    }

    @Transactional(readOnly = true)
    public Reply getReplyInfoExist(Long id) {
        return replyRepository.findById(id).orElseThrow(() -> new NoSuchElementException(GET_REPLIES_NOT_EXISTS_INFO.getMessage()));
    }

    @Transactional(readOnly = true)
    public ReplyResponse findOne(Long id) {
        return ReplyResponse.from(getReplyInfoExist(id));
    }
}
