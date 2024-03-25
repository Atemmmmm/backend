package com.artpro.artpro.member.controller;

import com.artpro.artpro.member.dto.LoginRequest;
import com.artpro.artpro.member.dto.TokenResponse;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseBody
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return memberService.login(loginRequest);
    }

    @PutMapping("/profile")
    public void updateProfileImage(@AuthenticationPrincipal Member member, @RequestPart MultipartFile image) {
        memberService.updateProfileImage(member.getEmail(), image);
    }
}