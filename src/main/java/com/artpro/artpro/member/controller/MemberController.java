package com.artpro.artpro.member.controller;

import com.artpro.artpro.member.dto.LoginRequest;
import com.artpro.artpro.member.dto.TokenResponse;
import com.artpro.artpro.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}