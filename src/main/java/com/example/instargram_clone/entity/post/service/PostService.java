package com.example.instargram_clone.entity.post.service;

import com.example.instargram_clone.aws.AwsS3Service;
import com.example.instargram_clone.config.BaseException;
import com.example.instargram_clone.entity.member.domain.Member;
import com.example.instargram_clone.entity.member.service.MemberService;
import com.example.instargram_clone.entity.post.dto.request.PostCreateRequest;
import com.example.instargram_clone.entity.post.dto.request.PostGetPostsListRequest;
import com.example.instargram_clone.entity.post.dto.request.PostUpdateRequest;
import com.example.instargram_clone.entity.post.dto.response.PostResponse;
import com.example.instargram_clone.entity.post.domain.Post;

import com.example.instargram_clone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.instargram_clone.config.BaseResponseStatus.GET_POSTS_NOT_EXISTS_INFO;
import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final MemberService memberService;
    private final AwsS3Service awsS3Service;

    @Transactional
    public void registerPost(PostCreateRequest postCreateRequest, MultipartFile multipartFile) throws IOException {
        Member member = memberService.getMemberInfoExist(postCreateRequest.getMember());
        postCreateRequest.setPosturl(awsS3Service.uploadImage(multipartFile));
        postRepository.save(postCreateRequest.toEntity(member));
    }

    @Transactional
    public void deletePostInfo(Long id) {
        Post post = getPostInfoExist(id);
        awsS3Service.deleteImage(post.getPosturl().split("/")[3]);
        postRepository.delete(getPostInfoExist(id));

    }

    @Transactional
    public void updatePostInfo(PostUpdateRequest postUpdateRequest, MultipartFile multipartFile) throws IOException {
        Post post = getPostInfoExist(postUpdateRequest.getId());
        Member member = memberService.getMemberInfoExist(postUpdateRequest.getMember());

        awsS3Service.deleteImage(post.getPosturl().split("/")[3]);
        postUpdateRequest.setPosturl(awsS3Service.uploadImage(multipartFile));

        post.update(postUpdateRequest.getContent(), member, postUpdateRequest.getPosturl());
    }

    @Transactional(readOnly = true)
    public PostResponse findOne(Long id) {
        Post post = getPostInfoExist(id);
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

        List<PostResponse> postsResponseList = postsPages.getContent().stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());

        return new PageImpl<>(postsResponseList, postsPages.getPageable(), postsPages.getTotalElements());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostListByMemberId(PostGetPostsListRequest postGetPostsListRequest) {
        List<Post> postTempList = postRepository.findPostsByMemberId(postGetPostsListRequest.getMember());
        return postTempList.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }
}

