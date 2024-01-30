package com.example.instargram_clone.repository;

import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByMemberId(Long memberId);

    Page<Post> findAllByMemberIn(List<Member> followingList, Pageable pageable);

//    List<Post> findAllByMemberId(Pageable pageable, Long memberId);

}
