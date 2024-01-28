package com.example.instargram_clone.entity.comment.service;

import com.example.instargram_clone.entity.comment.domain.Comment;
import com.example.instargram_clone.entity.comment.dto.request.CommentGetCommentsListRequest;
import com.example.instargram_clone.entity.comment.dto.request.CommentRegisterRequest;
import com.example.instargram_clone.entity.comment.dto.request.CommentUpdateRequest;
import com.example.instargram_clone.entity.comment.dto.response.CommentResponse;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.member.service.MemberService;
import com.example.instargram_clone.entity.post.domain.Post;
import com.example.instargram_clone.entity.post.dto.response.PostResponse;
import com.example.instargram_clone.entity.post.service.PostService;
import com.example.instargram_clone.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public CommentResponse findOne(Long id) {
        return CommentResponse.from(getCommentInfoExist(id));
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentListByPostId(CommentGetCommentsListRequest commentGetCommentsListRequest) {
        List<Comment> commentTempList = commentRepository.findCommentsByPostId(commentGetCommentsListRequest.getPost());
        return commentTempList.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }
}
