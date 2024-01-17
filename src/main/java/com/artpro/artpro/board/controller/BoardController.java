package com.artpro.artpro.board.controller;

import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.dto.response.BoardDetailResponse;
import com.artpro.artpro.board.dto.response.BoardResponse;
import com.artpro.artpro.board.service.BoardService;
import com.artpro.artpro.global.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/{category}")
    public Page<BoardResponse> getAllBoards(@PageableDefault(size = 8) Pageable pageable,
                                            @PathVariable String category,
                                            @RequestParam(defaultValue = "id", value = "orderby") String orderCriteria) {
        return boardService.getAllBoardByCategory(pageable, category, orderCriteria);
    }

    @GetMapping("/{category}/{boardId}")
    public BoardDetailResponse findByBoardId(@PathVariable long boardId) {
        return boardService.findByBoardId(boardId);
    }

    @DeleteMapping("/{boardId}")
    public void deleteById(@PathVariable long boardId) {
        boardService.deleteById(boardId);
    }

    @PutMapping("/{boardId}")
    public void updateById(@PathVariable long boardId,
                           CreateBoardRequest request,
                           @RequestPart MultipartFile song,
                           @RequestPart MultipartFile coverImage) {
        boardService.updateById(boardId, request, song, coverImage);
    }
}
