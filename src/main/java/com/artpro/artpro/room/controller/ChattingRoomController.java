package com.artpro.artpro.room.controller;

import com.artpro.artpro.room.service.ChattingRoomService;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/boards/{board_id}/room")
public class ChattingRoomController {

    private final ChattingRoomService chattingRoomService;

    @PostMapping
    public void create(@AuthenticationPrincipal Member member, @PathVariable(name = "board_id") Long boardId) {
        chattingRoomService.create(member, boardId);
    }
}
