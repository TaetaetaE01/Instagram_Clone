package com.example.instargram_clone.repository;

import com.example.instargram_clone.entity.comment.domain.Comment;
import com.example.instargram_clone.entity.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPostId(Long postId);
}
