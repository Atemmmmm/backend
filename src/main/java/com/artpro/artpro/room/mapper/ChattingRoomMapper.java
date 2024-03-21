package com.artpro.artpro.room.mapper;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.room.dto.response.RoomResponse;
import com.artpro.artpro.room.entity.ChattingRoom;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ChattingRoomMapper {

    public ChattingRoom mapToEntity(Member member, Board board) {
        return ChattingRoom.builder()
                .board(board)
                .createBy(member)
                .build();
    }

    public RoomResponse mapToRoomDto(ChattingRoom chattingRoom, Member member) {
        // TODO: 채팅 구현 후 마지막 메시지 추가
        return RoomResponse.builder()
                .counterpartNickname(findCounterpart(chattingRoom, member))
                .build();
    }

    private String findCounterpart(ChattingRoom chattingRoom, Member member) {
        if (Objects.equals(chattingRoom.getCreateBy().getId(), member.getId())) {
            return chattingRoom.getBoard().getMember().getNickname();
        }
        return chattingRoom.getCreateBy().getNickname();
    }
}
