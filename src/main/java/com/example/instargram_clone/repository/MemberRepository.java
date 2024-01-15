package com.example.instargram_clone.repository;


import com.example.instargram_clone.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MemberRepository extends JpaRepository<Member, Long> {

}
