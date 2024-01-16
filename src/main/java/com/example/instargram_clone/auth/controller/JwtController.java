package com.example.instargram_clone.auth.controller;

import com.example.instargram_clone.auth.dto.request.JwtCreateTokenRequest;
import com.example.instargram_clone.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instagram/auth")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody JwtCreateTokenRequest jwtCreateTokenRequest) {
        return ResponseEntity.ok().body(jwtService.createAccessToken(jwtCreateTokenRequest.getEmail()));
    }
}

