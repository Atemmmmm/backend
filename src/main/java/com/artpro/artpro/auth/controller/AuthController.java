package com.artpro.artpro.auth.controller;

import com.artpro.artpro.auth.dto.EmailRequest;
import com.artpro.artpro.auth.dto.NicknameRequest;
import com.artpro.artpro.auth.dto.PasswordRequest;
import com.artpro.artpro.auth.dto.RegisterRequest;
import com.artpro.artpro.auth.service.AuthService;
import com.artpro.artpro.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse register(@Validated @RequestBody RegisterRequest registerRequest) {
        return authService.create(registerRequest);
    }

    @PostMapping("/check-email")
    public boolean checkEmail(@RequestBody EmailRequest email) {
        return authService.checkEmail(email);
    }

    @PostMapping("/check-nickname")
    public boolean checkNickname(@RequestBody NicknameRequest nickname) {
        return authService.checkNickname(nickname);
    }

    @PostMapping("/check-password")
    public boolean checkPassword(@RequestBody PasswordRequest nickname) {
        return authService.checkPassword(nickname);
    }
}
