package com.artpro.artpro.heart.controller;

import com.artpro.artpro.global.dto.MemberDto;
import com.artpro.artpro.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/boards/{boardId}/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public void create(@PathVariable long boardId, @AuthenticationPrincipal MemberDto memberDto) {
        heartService.create(boardId, memberDto);
    }
}
