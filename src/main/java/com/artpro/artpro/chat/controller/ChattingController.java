package com.artpro.artpro.chat.controller;

import com.artpro.artpro.chat.dto.CreateMessage;
import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.chat.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChattingController {

    private final ChattingService chattingService;

    @MessageMapping("/{roomId}")
    @SendTo("/sub/{roomId}")
    public CreateMessage createMessage(@DestinationVariable Long roomId, MessageRequest messageRequest) {
        chattingService.createMessage(messageRequest, roomId);
        return CreateMessage.builder()
                .roomId(roomId)
                .message(messageRequest.getMessage())
                .senderNickname(messageRequest.getSenderNickname())
                .message(messageRequest.getMessage())
                .createAt(messageRequest.getCreateAt())
                .build();
    }
}
