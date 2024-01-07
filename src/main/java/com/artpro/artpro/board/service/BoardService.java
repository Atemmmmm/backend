package com.artpro.artpro.board.service;

import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.mapper.BoardMapper;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.global.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public void create(CreateBoardRequest request, MemberDto memberDto) {
        Board board = boardMapper.mapToEntity(request, memberDto);
        boardRepository.save(board);
    }
}
