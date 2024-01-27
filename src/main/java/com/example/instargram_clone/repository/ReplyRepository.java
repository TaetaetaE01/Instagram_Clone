package com.example.instargram_clone.repository;


import com.example.instargram_clone.entity.post.domain.Post;
import com.example.instargram_clone.entity.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findRepliesByCommentId(Long commentId);
}
