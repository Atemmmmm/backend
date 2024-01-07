package com.artpro.artpro.board.service;

import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.mapper.BoardMapper;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.file.repository.FileRepository;
import com.artpro.artpro.global.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public void create(CreateBoardRequest request,
                       MemberDto memberDto,
                       MultipartFile song,
                       MultipartFile coverImage) {
        String songUrl = fileRepository.save(song);
        String coverImageUrl = fileRepository.save(coverImage);
        Board board = boardMapper.mapToEntity(request, memberDto, songUrl, coverImageUrl);
        boardRepository.save(board);
    }
}
