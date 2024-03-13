package com.artpro.artpro.chat.mapper;

import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.chat.entity.Message;
import com.artpro.artpro.room.entity.ChattingRoom;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public Message toEntity(MessageRequest request, ChattingRoom chattingRoom) {
        return Message.builder()
                .message(request.getMessage())
                .sender(request.getSenderNickname())
                .chattingRoom(chattingRoom)
                .build();
    }
}
