package com.artpro.artpro.room.mapper;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.chat.dto.MessageResponse;
import com.artpro.artpro.chat.entity.Message;
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

    public RoomResponse mapToRoomDto(ChattingRoom chattingRoom, Member member, Message message) {
        return RoomResponse.builder()
                .counterpartNickname(findCounterpart(chattingRoom, member))
                .lastMessage(
                        MessageResponse.builder()
                                .senderNickname(message.getSender())
                                .message(message.getContent())
                                .type(message.getType())
                                .createAt(message.getCreateAt())
                                .build())
                .build();
    }

    private String findCounterpart(ChattingRoom chattingRoom, Member member) {
        if (Objects.equals(chattingRoom.getCreateBy().getId(), member.getId())) {
            return chattingRoom.getBoard().getMember().getNickname();
        }
        return chattingRoom.getCreateBy().getNickname();
    }
}
