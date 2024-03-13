package com.artpro.artpro.chat.controller;

import com.artpro.artpro.chat.dto.CreateMessage;
import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.member.entity.Member;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChattingController {

    @MessageMapping("/{roomId}")
    @SendTo("/sub/{roomId}")
    public CreateMessage createMessage(@AuthenticationPrincipal Member member, @DestinationVariable Long roomId, MessageRequest messageRequest) {
        return CreateMessage.builder()
                .roomId(roomId)
                .message(messageRequest.getMessage())
                .senderNickname(member.getNickname())
                .message(messageRequest.getMessage())
                .createAt(messageRequest.getCreateAt())
                .build();
    }
}
