package com.example.instargram_clone.repository;


import com.example.instargram_clone.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findRepliesByCommentId(Long commentId);
}
