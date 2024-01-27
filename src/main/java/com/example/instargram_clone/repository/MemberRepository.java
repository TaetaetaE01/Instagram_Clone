package com.example.instargram_clone.repository;


import com.example.instargram_clone.entity.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

}
