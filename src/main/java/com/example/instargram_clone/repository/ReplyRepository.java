package com.example.instargram_clone.repository;


import com.example.instargram_clone.entity.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
