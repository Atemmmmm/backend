package com.artpro.artpro.room.mapper;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.chat.dto.MessageResponse;
import com.artpro.artpro.chat.entity.Message;
import com.artpro.artpro.member.dto.ProfileResponse;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.room.dto.response.RoomResponse;
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

    public RoomResponse mapToRoomDto(ChattingRoom chattingRoom, Member counterpart, Message message) {
        return RoomResponse.builder()
                .roomId(chattingRoom.getId())
                .counterpart(new ProfileResponse(counterpart))
                .lastMessage(
                        MessageResponse.builder()
                                .senderEmail(message.getSender())
                                .message(message.getContent())
                                .type(message.getType())
                                .createAt(message.getCreateAt())
                                .build())
                .build();
    }
}
