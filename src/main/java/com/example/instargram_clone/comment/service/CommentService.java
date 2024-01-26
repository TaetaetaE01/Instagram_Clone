package com.example.instargram_clone.comment.service;

import com.example.instargram_clone.comment.domain.Comment;
import com.example.instargram_clone.comment.dto.request.CommentRegisterRequest;
import com.example.instargram_clone.comment.dto.request.CommentUpdateRequest;
import com.example.instargram_clone.member.domain.Member;
import com.example.instargram_clone.member.service.MemberService;
import com.example.instargram_clone.post.domain.Post;
import com.example.instargram_clone.post.service.PostService;
import com.example.instargram_clone.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.example.instargram_clone.config.BaseResponseStatus.GET_COMMENTS_NOT_EXISTS_INFO;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    public void registerPost(CommentRegisterRequest commentRegisterRequest) {
        Member member = memberService.getMemberInfoExist(commentRegisterRequest.getMember());
        Post post = postService.getPostInfoExist(commentRegisterRequest.getPost());
        commentRepository.save(commentRegisterRequest.toEntity(member, post));
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.delete(getCommentInfoExist(id));
    }

    @Transactional
    public void updateComment(CommentUpdateRequest commentUpdateRequest) {
        Comment comment = getCommentInfoExist(commentUpdateRequest.getId());
        comment.update(commentUpdateRequest.getContent());
    }

    @Transactional(readOnly = true)
    public Comment getCommentInfoExist(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException(GET_COMMENTS_NOT_EXISTS_INFO.getMessage()));
    }
}
