package com.example.instargram_clone.member.controller;


import com.example.instargram_clone.member.dto.request.MemberCreateRequest;
import com.example.instargram_clone.member.dto.request.MemberUpdateRequest;
import com.example.instargram_clone.member.dto.response.MemberResponse;
import com.example.instargram_clone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instagram/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> registerMember(@RequestBody MemberCreateRequest memberCreateRequest) {
        memberService.registerMember(memberCreateRequest);
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMemberInfo(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(memberUpdateRequest);
        return ResponseEntity.ok().build();
    }
}