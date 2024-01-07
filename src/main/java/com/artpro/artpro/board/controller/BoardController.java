package com.artpro.artpro.board.controller;

import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.service.BoardService;
import com.artpro.artpro.global.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void create(@AuthenticationPrincipal MemberDto memberDto,
                       CreateBoardRequest request,
                       @RequestPart MultipartFile song,
                       @RequestPart MultipartFile coverImage) {
        boardService.create(request, memberDto, song, coverImage);
    }
}
