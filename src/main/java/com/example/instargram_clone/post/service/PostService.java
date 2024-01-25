package com.example.instargram_clone.post.service;

import com.example.instargram_clone.member.domain.Member;
import com.example.instargram_clone.member.service.MemberService;
import com.example.instargram_clone.post.domain.Post;
import com.example.instargram_clone.post.dto.request.PostCreateRequest;
import com.example.instargram_clone.post.dto.request.PostUpdateRequest;
import com.example.instargram_clone.post.dto.response.PostResponse;
import com.example.instargram_clone.repository.MemberRepository;

import com.example.instargram_clone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;

import static com.example.instargram_clone.config.BaseResponseStatus.GET_POSTS_NOT_EXISTS_INFO;
import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    //    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    private final MemberService memberService;

    @Transactional
    public void registerPost(PostCreateRequest postCreateRequest) {
        Member member = memberService.getMemberInfoExist(postCreateRequest.getMember());
        postRepository.save(postCreateRequest.toEntity(member));
    }

    @Transactional
    public void deletePostInfo(Long id) {
        // delete (Comment, reply 다 지우기)
        if (checkPostInfoExist(id)) {
            postRepository.delete(getPostInfoExist(id));
        }
//        List<Reply> replyList = replyRepository.findByBoardId(id);
//        board.setReplyList(replyList);
    }

    @Transactional
    public void updatePostInfo(PostUpdateRequest postUpdateRequest) {
        Post post = getPostInfoExist(postUpdateRequest.getId());
        Member member = memberService.getMemberInfoExist(postUpdateRequest.getId());
        post.update(postUpdateRequest.getContent(), member, postUpdateRequest.getPosturl());
    }

    @Transactional(readOnly = true)
    public PostResponse findOne(Long id) {
        Post post = getPostInfoExist(id);

        // 피드글에 있는 댓글, 답글도 가져와야 함
//        List<Reply> replyList = replyRepository.findByBoardId(id);
//        board.setReplyList(replyList);
        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public Post getPostInfoExist(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(GET_POSTS_NOT_EXISTS_INFO.getMessage()));
        return post;
    }

    @Transactional(readOnly = true)
    public boolean checkPostInfoExist(Long id) {
        postRepository.findById(id).orElseThrow(() -> new NoSuchElementException(GET_POSTS_NOT_EXISTS_INFO.getMessage()));
        return true;
    }



    @Transactional(readOnly = true)
    public Page<PostResponse> paging(Pageable pageable) {

        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 3; // 한 페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 디폴트 값으로
        Page<Post> postsPages = postRepository.findAll(PageRequest.of(page, pageLimit));

        Page<PostResponse> postsResponsePages = (Page<PostResponse>) postsPages.stream().map(PostResponse::from).toList();
        return postsResponsePages;
    }
}
