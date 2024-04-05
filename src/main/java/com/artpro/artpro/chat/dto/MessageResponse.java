package com.artpro.artpro.chat.dto;

import com.artpro.artpro.chat.entity.Type;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponse {

    private String message;
    private String senderEmail;
    private LocalDateTime createAt;
    private Type type;
}
