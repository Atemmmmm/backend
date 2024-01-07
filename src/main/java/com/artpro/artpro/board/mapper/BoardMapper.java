package com.artpro.artpro.board.mapper;

import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.global.dto.MemberDto;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public Board mapToEntity(CreateBoardRequest dto, MemberDto memberDto) {
        return Board.builder()
                .cover(dto.cover())
                .song(dto.song())
                .title(dto.title())
                .member(memberDto.member())
                .build();
    }
}
