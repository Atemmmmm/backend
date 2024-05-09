package com.artpro.artpro.chat.mapper;

import com.artpro.artpro.chat.dto.ChattingListResponse;
import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.chat.dto.MessageResponse;
import com.artpro.artpro.chat.entity.Message;
import com.artpro.artpro.member.dto.ProfileResponse;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.room.entity.ChattingRoom;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ChattingListResponse toDto(List<Message> message, Member counterpart) {
        return ChattingListResponse.builder()
                .messages(toMessageResponse(message))
                .counterpart(toProfileResponse(counterpart))
                .build();
    }

    private List<MessageResponse> toMessageResponse(List<Message> messages) {
        return messages.stream()
                .map(MessageResponse::new)
                .toList();
    }

    private ProfileResponse toProfileResponse(Member counterpart) {
        return new ProfileResponse(counterpart);
    }
}
