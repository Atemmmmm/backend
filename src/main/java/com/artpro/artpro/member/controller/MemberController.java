package com.artpro.artpro.member.controller;

import com.artpro.artpro.member.dto.*;
import com.artpro.artpro.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse register(@Validated @RequestBody RegisterRequest registerRequest) {
        return memberService.create(registerRequest);
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

    @PostMapping("/password")
    @ResponseBody
    public MessageResponse checkPassword(@RequestBody PasswordRequest nickname) {
        return memberService.checkPassword(nickname);
    }
}