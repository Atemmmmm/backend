package com.artpro.artpro.room.mapper;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.room.entity.ChattingRoom;
import org.springframework.stereotype.Component;

@Component
public class ChattingRoomMapper {

    public ChattingRoom mapToEntity(Member member, Board board) {
        return ChattingRoom.builder()
                .board(board)
                .createBy(member)
                .build();
    }
}
