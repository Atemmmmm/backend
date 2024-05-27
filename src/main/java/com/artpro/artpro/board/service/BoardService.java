package com.artpro.artpro.board.service;

import com.artpro.artpro.board.dto.request.BoardParameter;
import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.dto.response.BoardDetailResponse;
import com.artpro.artpro.board.dto.response.BoardResponse;
import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.entity.Category;
import com.artpro.artpro.board.exception.BoardNotFoundException;
import com.artpro.artpro.board.mapper.BoardMapper;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.file.repository.FileRepository;
import com.artpro.artpro.heart.entity.Heart;
import com.artpro.artpro.heart.repository.HeartRepository;
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
    private final HeartRepository heartRepository;
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

    public Page<BoardResponse> getAllBoards(Pageable pageable, BoardParameter parameter) {
        if (parameter.getCategory() == Category.ALL) {
            return findAll(pageable, parameter);
        }
        return getAllBoardByCategory(pageable, parameter);
    }

    private Page<BoardResponse> findAll(Pageable pageable, BoardParameter parameter) {
        pageable = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, parameter.getOrderBy()));
        return boardRepository.findAll(pageable)
                .map(BoardResponse::new);
    }

    private Page<BoardResponse> getAllBoardByCategory(Pageable pageable, BoardParameter parameter) {
        pageable = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, parameter.getOrderBy()));
        return boardRepository.findAllByCategoryAndGenre(pageable, parameter.getCategory(), parameter.getGenre())
                .map(BoardResponse::new);
    }

    public BoardDetailResponse findByBoardId(long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        return new BoardDetailResponse(board);
    }

    @Transactional
    public void deleteById(long boardId) {
        heartRepository.findAllByBoard_Id(boardId)
                        .ifPresent(hearts -> hearts.forEach(Heart::delete));
        boardRepository.findById(boardId)
                .ifPresent(Board::delete);

    }

    @Transactional
    public void updateById(long boardId,
                           CreateBoardRequest request,
                           MultipartFile song,
                           MultipartFile cover) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        String songUrl = updateSong(song, board);
        String coverImageUrl = updateCoverImg(cover, board);
        board.update(request.title(), request.genre(), coverImageUrl, songUrl);
    }

    private String updateSong(MultipartFile song, Board board) {
        if (song != null) {
            return fileRepository.save(song);
        }
        return board.getSong();
    }

    private String updateCoverImg(MultipartFile image, Board board) {
        if (image != null) {
            return fileRepository.save(image);
        }
        return board.getCover();
    }
}
