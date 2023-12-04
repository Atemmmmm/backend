package com.artpro.artpro.member.controller;

import com.artpro.artpro.member.dto.RegisterRequest;
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

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Void> register(@Validated @RequestBody RegisterRequest registerRequest) {
        memberService.create(registerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
