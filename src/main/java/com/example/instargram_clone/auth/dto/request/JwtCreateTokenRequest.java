package com.example.instargram_clone.auth.dto.request;

import lombok.Data;

@Data
public class JwtCreateTokenRequest {
    private String email;
    private String password;
}
