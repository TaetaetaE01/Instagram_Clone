package com.example.instargram_clone.entity.member.controller;


import com.example.instargram_clone.auth.service.JwtService;
import com.example.instargram_clone.entity.member.dto.request.MemberCreateRequest;
import com.example.instargram_clone.entity.member.dto.request.MemberUpdateRequest;
import com.example.instargram_clone.entity.member.dto.response.MemberResponse;
import com.example.instargram_clone.entity.member.service.MemberService;
import com.example.instargram_clone.entity.post.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/instagram/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<Void> registerMember(@RequestPart(value = "memberCreateRequest") MemberCreateRequest memberCreateRequest, @RequestPart(value = "imageFile") MultipartFile multipartFile) throws IOException {
        memberService.registerMember(memberCreateRequest, multipartFile);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberInfo(@PathVariable("id") Long id) {
        memberService.deleteMemberInfo(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberInfo(@PathVariable("id") Long id) {
        MemberResponse memberResponse = memberService.getMemberInfo(id);
        return ResponseEntity.ok(memberResponse);
    }

    @PutMapping
    public ResponseEntity<Void> updateMemberInfo(@RequestPart(value = "memberUpdateRequest") MemberUpdateRequest memberUpdateRequest, @RequestPart(value = "imageFile") MultipartFile multipartFile) throws IOException {
        memberService.updateMember(memberUpdateRequest, multipartFile);
        return ResponseEntity.ok().build();
    }
}