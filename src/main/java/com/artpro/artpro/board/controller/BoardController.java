package com.artpro.artpro.board.controller;

import com.artpro.artpro.board.dto.request.BoardParameter;
import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.dto.response.BoardDetailResponse;
import com.artpro.artpro.board.dto.response.BoardResponse;
import com.artpro.artpro.board.service.BoardService;
import com.artpro.artpro.member.entity.Member;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@AuthenticationPrincipal Member member,
                       CreateBoardRequest request,
                       @Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
                       @RequestPart MultipartFile song,
                       @RequestPart MultipartFile coverImage) {
        boardService.create(request, member, song, coverImage);
    }

    @GetMapping()
    public Page<BoardResponse> getAllBoards(@PageableDefault(size = 6) Pageable pageable,
                                            @ModelAttribute BoardParameter parameter) {
        return boardService.getAllBoardByCategory(pageable, parameter);
    }

    @GetMapping("/{boardId}")
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