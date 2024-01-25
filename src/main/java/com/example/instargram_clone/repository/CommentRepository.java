package com.example.instargram_clone.repository;

import com.example.instargram_clone.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
