package com.example.instargram_clone.member.service;

import com.example.instargram_clone.member.domain.Member;
import com.example.instargram_clone.member.dto.request.MemberCreateRequest;
import com.example.instargram_clone.member.dto.request.MemberUpdateRequest;
import com.example.instargram_clone.member.dto.response.MemberResponse;
import com.example.instargram_clone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
//    private final BoardRepository boardRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void registerMember(MemberCreateRequest memberCreateRequest) {
        //filter(email, name 정보 있으면 back / pw jwt암호화)
        memberCreateRequest.setPassword(bCryptPasswordEncoder.encode(memberCreateRequest.getPassword()));
        memberRepository.save(memberCreateRequest.toEntity());
    }

    @Transactional(readOnly = true)
    public void deleteMemberInfo(Long id) {
        if (checkMemberInfoExist(id)) {
            memberRepository.delete(getMemberInfoExist(id));
        }
    }

    @Transactional(readOnly = true)
    public MemberResponse getMemberInfo(Long id) {
        Member member = getMemberInfoExist(id);
        return MemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public Member getMemberInfoExist(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));
        return member;
    }

    @Transactional(readOnly = true)
    public boolean checkMemberInfoExist(Long id) {
        memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));
        return true;
    }

    @Transactional
    public void updateMember(MemberUpdateRequest memberUpdateRequest) {
        Member member = getMemberInfoExist(memberUpdateRequest.getId());
        member.update(memberUpdateRequest.getEmail(), memberUpdateRequest.getName(), memberUpdateRequest.getPassword(), memberUpdateRequest.getProfileURL(), memberUpdateRequest.getStatusMessage());
    }
}
