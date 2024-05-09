package com.artpro.artpro.chat.dto;

import com.artpro.artpro.chat.entity.Message;
import com.artpro.artpro.chat.entity.Type;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponse {

    private final String message;
    private final String senderEmail;
    private final LocalDateTime createAt;
    private final Type type;

    public MessageResponse(Message message) {
        this.message = message.getContent();
        this.senderEmail = message.getSender();
        this.createAt = message.getCreateAt();
        this.type = message.getType();
    }
}
