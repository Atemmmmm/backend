package com.artpro.artpro.board.service;

import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.dto.response.BoardDetailResponse;
import com.artpro.artpro.board.dto.response.BoardResponse;
import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.exception.BoardNotFoundException;
import com.artpro.artpro.board.mapper.BoardMapper;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.file.repository.FileRepository;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Transactional
    public void create(CreateBoardRequest request,
                       Member member,
                       MultipartFile song,
                       MultipartFile coverImage) {
        String songUrl = fileRepository.save(song);
        String coverImageUrl = fileRepository.save(coverImage);
        Board board = boardMapper.mapToEntity(request, member, songUrl, coverImageUrl);
        boardRepository.save(board);
    }

    public Page<BoardResponse> getAllBoardByCategory(Pageable pageable, String category, String orderCriteria) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orderCriteria));
        return boardRepository.findAllByCategory(pageable, category)
                .map(BoardResponse::new);
    }

    public BoardDetailResponse findByBoardId(long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        return new BoardDetailResponse(board);
    }

    @Transactional
    public void deleteById(long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public void updateById(long boardId,
                           CreateBoardRequest request,
                           MultipartFile song,
                           MultipartFile coverImage) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        String songUrl = fileRepository.save(song);
        String coverImageUrl = fileRepository.save(coverImage);
        board.update(request.title(), request.genre(), songUrl, coverImageUrl);
    }
}
