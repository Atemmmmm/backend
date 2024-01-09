package com.artpro.artpro.heart.controller;

import com.artpro.artpro.global.dto.MemberDto;
import com.artpro.artpro.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/boards/{boardId}/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public void create(@PathVariable long boardId, @AuthenticationPrincipal MemberDto memberDto) {
        heartService.create(boardId, memberDto);
    }

    @DeleteMapping("/{heartId}")
    public void delete(@PathVariable long boardId, @PathVariable long heartId) {
        heartService.delete(heartId);
    }
}
