package com.artpro.artpro.heart.service;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.exception.BoardNotFoundException;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.heart.dto.HeartResponse;
import com.artpro.artpro.heart.entity.Heart;
import com.artpro.artpro.heart.exception.ExistingHeartException;
import com.artpro.artpro.heart.exception.HeartNotFoundException;
import com.artpro.artpro.heart.mapper.HeartMapper;
import com.artpro.artpro.heart.repository.HeartRepository;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;
    private final HeartMapper mapper;

    @Transactional
    public HeartResponse create(Long boardId, Member member) {
        validateExisting(boardId, member);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        Heart heart = mapper.mapToEntity(member, board);
        heartRepository.save(heart);
        board.updateLikeCount(board.getLikeCount() + 1);
        return mapper.mapToHeartResponse(getLikeCount(boardId), heart);
    }

    private void validateExisting(Long boardId, Member member) {
        if (isHeart(boardId, member)) {
            throw new ExistingHeartException();
        }
    }

    private boolean isHeart(Long boardId, Member member) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        return heartRepository.findHeartByBoardAndMember(board, member)
                .orElse(new ArrayList<>())
                .stream()
                .anyMatch(Heart::isValid);
    }

    @Transactional
    public void delete(Long heartId) {
        Heart heart = heartRepository.findById(heartId)
                .orElseThrow(HeartNotFoundException::new);
        Board board = heart.getBoard();
        heart.delete();
        board.updateLikeCount(board.getLikeCount() - 1);
    }

    public HeartResponse findHeartByBoardIdAndMemberId(Long boardId, Member member) {
        return heartRepository.findHeartByBoard_IdAndMember(boardId, member)
                .orElse(new ArrayList<>())
                .stream()
                .filter(Heart::isValid)
                .findAny()
                .map(heart -> mapper.mapToHeartResponse(getLikeCount(boardId) , heart))
                .orElse(mapper.mapToHeartResponse(getLikeCount(boardId)));
    }

    private int getLikeCount(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        return board.getLikeCount();
    }
}
