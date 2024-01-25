package com.example.instargram_clone.member.service;

import com.example.instargram_clone.config.BaseResponseStatus;
import com.example.instargram_clone.member.domain.Member;
import com.example.instargram_clone.member.dto.request.MemberCreateRequest;
import com.example.instargram_clone.member.dto.request.MemberUpdateRequest;
import com.example.instargram_clone.member.dto.response.MemberResponse;
import com.example.instargram_clone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.example.instargram_clone.config.BaseResponseStatus.GET_MEMBERS_NOT_EXISTS_INFO;

@Service
@RequiredArgsConstructor
public class MemberService {

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
                .orElseThrow(() -> new NoSuchElementException(GET_MEMBERS_NOT_EXISTS_INFO.getMessage()));
        return member;
    }

    @Transactional(readOnly = true)
    public boolean checkMemberInfoExist(Long id) {
        memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException(GET_MEMBERS_NOT_EXISTS_INFO.getMessage()));
        return true;
    }

    @Transactional
    public void updateMember(MemberUpdateRequest memberUpdateRequest) {
        Member member = getMemberInfoExist(memberUpdateRequest.getId());
        memberUpdateRequest.setPassword(bCryptPasswordEncoder.encode(memberUpdateRequest.getPassword()));
        member.update(memberUpdateRequest.getEmail(), memberUpdateRequest.getName(), memberUpdateRequest.getPassword(), memberUpdateRequest.getProfileurl(), memberUpdateRequest.getStatusMessage());
    }
}
