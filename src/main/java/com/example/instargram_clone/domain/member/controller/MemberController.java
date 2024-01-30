package com.example.instargram_clone.domain.member.controller;


import com.example.instargram_clone.domain.member.dto.request.MemberCreateRequest;
import com.example.instargram_clone.domain.member.dto.request.MemberLoginRequest;
import com.example.instargram_clone.domain.member.dto.request.MemberUpdateRequest;
import com.example.instargram_clone.domain.member.dto.response.MemberResponse;
import com.example.instargram_clone.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/instagram/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/singUp")
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        String token = memberService.login(memberLoginRequest);
        return ResponseEntity.ok().body(token);
    }
}