package com.artpro.artpro.room.controller;

import com.artpro.artpro.room.service.ChattingRoomService;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChattingRoomController {

    private final ChattingRoomService chattingRoomService;

    @PostMapping
    public void create(@AuthenticationPrincipal Member member, Long boardId) {

    }
}
