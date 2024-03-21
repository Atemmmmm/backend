package com.artpro.artpro.room.controller;

import com.artpro.artpro.room.dto.response.RoomResponse;
import com.artpro.artpro.room.service.ChattingRoomService;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/room")
public class ChattingRoomController {

    private final ChattingRoomService chattingRoomService;

    @PostMapping("/boards/{board_id}")
    public void create(@AuthenticationPrincipal Member member, @PathVariable(name = "board_id") Long boardId) {
        chattingRoomService.create(member, boardId);
    }

    @GetMapping
    public List<RoomResponse> findByMemberId(@AuthenticationPrincipal Member member) {
        return chattingRoomService.findByMemberId(member);
    }
}
