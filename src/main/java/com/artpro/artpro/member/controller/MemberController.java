package com.artpro.artpro.member.controller;

import com.artpro.artpro.member.dto.*;
import com.artpro.artpro.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    @ResponseBody
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return memberService.login(loginRequest);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Void> register(@Validated @RequestBody RegisterRequest registerRequest) {
        memberService.create(registerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/email")
    @ResponseBody
    public MessageResponse checkEmail(@RequestBody EmailRequest email) {
        return memberService.checkEmail(email);
    }

    @PostMapping("/nickname")
    @ResponseBody
    public MessageResponse checkNickname(@RequestBody NicknameRequest nickname) {
        return memberService.checkNickname(nickname);
    }
}