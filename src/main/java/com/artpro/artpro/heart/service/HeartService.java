package com.artpro.artpro.heart.service;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.exception.BoardNotFoundException;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.global.dto.MemberDto;
import com.artpro.artpro.heart.entity.Heart;
import com.artpro.artpro.heart.exception.HeartNotFoundException;
import com.artpro.artpro.heart.mapper.HeartMapper;
import com.artpro.artpro.heart.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void create(Long boardId, MemberDto memberDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        HeartMapper mapper = new HeartMapper();
        Heart heart = mapper.mapToEntity(memberDto.member() , board);
        heartRepository.save(heart);
    }

    @Transactional
    public void delete(Long heartId) {
        Heart heart = heartRepository.findById(heartId)
                .orElseThrow(HeartNotFoundException::new);
        heart.delete();
    }
}
