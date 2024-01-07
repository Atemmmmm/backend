package com.artpro.artpro.board.controller;

import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.service.BoardService;
import com.artpro.artpro.global.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void create(@AuthenticationPrincipal MemberDto memberDto, @RequestBody CreateBoardRequest request) {
        boardService.create(request, memberDto);
    }
}
