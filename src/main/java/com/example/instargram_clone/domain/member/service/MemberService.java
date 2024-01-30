package com.example.instargram_clone.domain.member.service;

import com.example.instargram_clone.auth.service.JwtService;
import com.example.instargram_clone.aws.AwsS3Service;
import com.example.instargram_clone.config.BaseException;
import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.member.dto.request.MemberCreateRequest;
import com.example.instargram_clone.domain.member.dto.request.MemberLoginRequest;
import com.example.instargram_clone.domain.member.dto.request.MemberUpdateRequest;
import com.example.instargram_clone.domain.member.dto.response.MemberResponse;
import com.example.instargram_clone.repository.MemberRepository;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

import static com.example.instargram_clone.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final AwsS3Service awsS3Service;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void registerMember(MemberCreateRequest memberCreateRequest, MultipartFile multipartFile) throws IOException {

        if (memberRepository.existsByEmail(memberCreateRequest.getEmail())) {
            throw new BaseException(POST_MEMBER_INFO_EXISTS.getMessage());
        }

        Member member = memberCreateRequest.toEntity();
        member.encodePassword(bCryptPasswordEncoder);
        member.setProfileurl(awsS3Service.uploadImage(multipartFile));

        memberRepository.save(memberCreateRequest.toEntity());
    }

    @Transactional
    public void deleteMemberInfo(Long id) {
        Member member = getMemberInfoExist(id);
        awsS3Service.deleteImage(member.getProfileurl().split("/")[3]);
        memberRepository.delete(getMemberInfoExist(id));
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
    public void updateMember(MemberUpdateRequest memberUpdateRequest, MultipartFile multipartFile) throws IOException {
        Member member = getMemberInfoExist(memberUpdateRequest.getId());

        awsS3Service.deleteImage(member.getProfileurl().split("/")[3]);

        memberUpdateRequest.setProfileurl(awsS3Service.uploadImage(multipartFile));

        memberUpdateRequest.setPassword(bCryptPasswordEncoder.encode(memberUpdateRequest.getPassword()));
        member.update(memberUpdateRequest.getEmail(), memberUpdateRequest.getName(), memberUpdateRequest.getPassword(), memberUpdateRequest.getProfileurl(), memberUpdateRequest.getStatusMessage());
    }

    @Transactional
    public String login(MemberLoginRequest memberLoginRequest) throws NoSuchElementException {

        if (!memberRepository.existsByEmail(memberLoginRequest.getEmail())) {
            throw new NoSuchElementException(POST_EMAIL_NOT_EXISTS_INFO.getMessage());
        }

        String dbPassword = memberRepository.findByEmail(memberLoginRequest.getEmail()).getPassword();
        if (!bCryptPasswordEncoder.matches(memberLoginRequest.getPw(), dbPassword)) {
            throw new IllegalArgumentException(POST_PASSWORD_NOT_MATCH.getMessage());
        }

        return jwtService.createAccessToken(memberLoginRequest.getEmail());
    }
}
