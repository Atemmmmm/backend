package com.artpro.artpro.chat.controller;

import com.artpro.artpro.chat.dto.CreateMessage;
import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.chat.dto.MessageResponse;
import com.artpro.artpro.chat.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChattingController {

    private final ChattingService chattingService;

    @MessageMapping("/{roomId}")
    @SendTo("/sub/{roomId}")
    public CreateMessage createMessage(@DestinationVariable Long roomId, MessageRequest messageRequest) {
        chattingService.createMessage(messageRequest, roomId);
        System.out.println(messageRequest.getMessage());
        return CreateMessage.builder()
                .roomId(roomId)
                .message(messageRequest.getMessage())
                .senderNickname(messageRequest.getSenderNickname())
                .message(messageRequest.getMessage())
                .createAt(messageRequest.getCreateAt())
                .build();
    }

    @GetMapping("/room/{roomId}")
    public List<MessageResponse> findAllMessageByRoomId(@PathVariable Long roomId) {
        return chattingService.findAllByRoomId(roomId);
    }
}
