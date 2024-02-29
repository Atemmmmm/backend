package com.artpro.artpro.board.mapper;

import com.artpro.artpro.board.dto.request.CreateBoardRequest;
import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public Board mapToEntity(CreateBoardRequest dto, Member member, String songUrl, String imageUrl) {
        return Board.builder()
                .cover(imageUrl)
                .song(songUrl)
                .title(dto.title())
                .genre(dto.genre())
                .member(member)
                .build();
    }
}
