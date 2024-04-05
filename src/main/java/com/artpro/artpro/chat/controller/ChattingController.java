package com.artpro.artpro.chat.controller;

import com.artpro.artpro.chat.dto.CreateMessage;
import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.chat.dto.MessageResponse;
import com.artpro.artpro.chat.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
                .senderEmail(messageRequest.getSenderEmail())
                .message(messageRequest.getMessage())
                .createAt(messageRequest.getCreateAt())
                .type(messageRequest.getType())
                .build();
    }

    @GetMapping("/room/{roomId}")
    public List<MessageResponse> findAllMessageByRoomId(@PathVariable Long roomId) {
        return chattingService.findAllByRoomId(roomId);
    }

    @PostMapping("/room/{roomId}")
    public String saveImage(@PathVariable Long roomId, @RequestPart MultipartFile file) {
        return chattingService.saveFile(file);
    }
}
