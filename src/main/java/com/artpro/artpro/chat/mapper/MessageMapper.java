package com.artpro.artpro.chat.mapper;

import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.chat.dto.MessageResponse;
import com.artpro.artpro.chat.entity.Message;
import com.artpro.artpro.room.entity.ChattingRoom;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public Message toEntity(MessageRequest request, ChattingRoom chattingRoom) {
        return Message.builder()
                .content(request.getMessage())
                .sender(request.getSenderEmail())
                .createAt(request.getCreateAt())
                .chattingRoom(chattingRoom)
                .type(request.getType())
                .build();
    }

    public Message toEntity(String url, MessageRequest request, ChattingRoom chattingRoom) {
        return Message.builder()
                .content(url)
                .sender(request.getSenderEmail())
                .createAt(request.getCreateAt())
                .chattingRoom(chattingRoom)
                .type(request.getType())
                .build();
    }

    public MessageResponse toDto(Message entity) {
        return MessageResponse.builder()
                .message(entity.getContent())
                .senderEmail(entity.getSender())
                .createAt(entity.getCreateAt())
                .type(entity.getType())
                .build();
    }
}
