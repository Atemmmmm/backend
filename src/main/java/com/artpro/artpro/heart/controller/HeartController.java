package com.artpro.artpro.heart.controller;

import com.artpro.artpro.heart.dto.HeartResponse;
import com.artpro.artpro.heart.service.HeartService;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hearts")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HeartResponse create(@RequestParam long boardId, @AuthenticationPrincipal Member member) {
        return heartService.create(boardId, member);
    }

    @DeleteMapping("/{heartId}")
    public void delete(@PathVariable long heartId) {
        heartService.delete(heartId);
    }


    @GetMapping
    public HeartResponse isHeart(@RequestParam long boardId, @AuthenticationPrincipal Member member) {
        return heartService.findHeartByBoardIdAndMemberId(boardId, member);
    }
}
