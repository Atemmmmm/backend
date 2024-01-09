package com.artpro.artpro.heart.mapper;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.heart.entity.Heart;
import com.artpro.artpro.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class HeartMapper {

    public Heart mapToEntity(Member member, Board board) {
        return Heart.builder()
                .member(member)
                .board(board)
                .build();
    }
}
