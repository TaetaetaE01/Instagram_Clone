package com.example.instargram_clone;

import com.example.instargram_clone.aws.AwsS3Service;
import com.example.instargram_clone.domain.follow.entity.Follow;
import com.example.instargram_clone.domain.follow.service.FollowService;
import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.member.service.MemberService;
import com.example.instargram_clone.domain.post.dto.request.PostCreateRequest;
import com.example.instargram_clone.domain.post.dto.request.PostGetFeedPagingRequest;
import com.example.instargram_clone.domain.post.dto.request.PostGetPostsListRequest;
import com.example.instargram_clone.domain.post.dto.request.PostUpdateRequest;
import com.example.instargram_clone.domain.post.dto.response.PostResponse;
import com.example.instargram_clone.domain.post.entity.Post;
import com.example.instargram_clone.domain.post.service.PostService;
import com.example.instargram_clone.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PostRepository postRepository;

    @Mock
    private MemberService memberService;

    @Mock
    private FollowService followService;

    @Mock
    private AwsS3Service awsS3Service;

    @InjectMocks
    private PostService postService;


    @Test
    public void registerPostTest() throws Exception {
        Member member = Member.builder().id(1L).build();
        when(memberService.getMemberInfoExist(any())).thenReturn(member);

        List<Class<MultipartFile>> mockMultipartFiles = Arrays.asList(MultipartFile.class);

        when(awsS3Service.uploadImage(any(MultipartFile.class))).thenReturn("testURL");

        mockMvc.perform(post("")
                        .param("member", "someMemberInfo")
                        .flashAttr("postCreateRequest", new PostCreateRequest("Test Content", 1L))
                        .flashAttr("multipartFiles", mockMultipartFiles))
                .andExpect(status().isOk());
    }


    @Test
    void deletePostInfoTest() {
        // Given
        Long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(Post.builder().build()));

        // When
        postService.deletePostInfo(postId);

        // Then
        verify(postRepository, times(1)).delete(any(Post.class));
    }

    @Test
    void updatePostInfoTest() throws IOException {
        // Given
        PostUpdateRequest postUpdateRequest = new PostUpdateRequest(1L, "Update Content", 1L);
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);

        when(postRepository.findById(postUpdateRequest.getId())).thenReturn(java.util.Optional.of(Post.builder().build()));
        when(memberService.getMemberInfoExist(postUpdateRequest.getMember())).thenReturn(Member.builder().id(1L).build());
        when(awsS3Service.uploadImage(multipartFile)).thenReturn("updated-image-url");

        // When
        postService.updatePostInfo(postUpdateRequest, multipartFile);

        // Then
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void findOneTest() {
        // Given
        Long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(Post.builder().build()));
        // When
        PostResponse postResponse = postService.findOne(postId);
    }

    @Test
    void pagingTest() {
        // Given
        int page = 0;
        int pageLimit = 3;
        Pageable pageable = PageRequest.of(page, pageLimit);
        when(postRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));
        // When
        Page<PostResponse> result = postService.paging(pageable);
    }

    @Test
    void feedPagingTest() {
        // Given
        int page = 0;
        int pageLimit = 3;
        Pageable pageable = PageRequest.of(page, pageLimit);
        PostGetFeedPagingRequest postGetFeedPagingRequest = new PostGetFeedPagingRequest(1L);

        Follow follow = Follow.builder().id(1L).following(Member.builder().id(2L).build()).build();
        when(followService.getFollowInfo(postGetFeedPagingRequest.getMember())).thenReturn(List.of(follow));
        when(postRepository.findAllByMemberIn(List.of(follow.getFollowing()), pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));
        // When
        Page<PostResponse> result = postService.feedPaging(postGetFeedPagingRequest, pageable);
    }

    @Test
    void getPostListByMemberIdTest() {
        // Given
        PostGetPostsListRequest postGetPostsListRequest = new PostGetPostsListRequest(1L);
        when(postRepository.findPostsByMemberId(postGetPostsListRequest.getMember())).thenReturn(Collections.emptyList());
        // When
        List<PostResponse> result = postService.getPostListByMemberId(postGetPostsListRequest);
    }
}