package com.example.instargram_clone;

import com.example.instargram_clone.auth.service.JwtService;
import com.example.instargram_clone.aws.AwsS3Service;
import com.example.instargram_clone.domain.member.dto.request.MemberCreateRequest;
import com.example.instargram_clone.domain.member.dto.response.MemberResponse;
import com.example.instargram_clone.domain.member.entity.Member;
import com.example.instargram_clone.domain.member.service.MemberService;
import com.example.instargram_clone.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AwsS3Service awsS3Service;

    @Mock
    private JwtService jwtService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    private MemberService memberService;


    @Test
    void registerMember() throws IOException {
        MemberCreateRequest request = new MemberCreateRequest("test@naver.com", "TestTaeTae", "password");
        Member member = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .build();
        when(memberRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(memberRepository.save(any(Member.class))).thenReturn(member);  // 수정된 부분

        // Act
        memberService.registerMember(request, null);

        // Assert
        verify(memberRepository, times(1)).existsByEmail(request.getEmail());
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void testDeleteMemberInfo() throws Exception {
        // Given
        Long memberId = 1L;

        // Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/instagram/members/{id}", memberId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(memberService, Mockito.times(1)).deleteMemberInfo(eq(memberId));
    }

    @Test
    public void testGetMemberInfo() throws Exception {
        // Given
        Long memberId = 1L;
        MemberResponse memberResponse = new MemberResponse("test@example.com", "TestUser", "http://example.com/profile.jpg");

        // When
        Mockito.when(memberService.getMemberInfo(any(Long.class))).thenReturn(memberResponse);

        // Then
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/instagram/members/{id}", memberId));

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("TestUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profileUrl").value("http://example.com/profile.jpg"));

        Mockito.verify(memberService, Mockito.times(1)).getMemberInfo(eq(memberId));
    }
}
