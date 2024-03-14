package com.artpro.artpro.chat.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponse {

    private String message;
    private String sender;
    private LocalDateTime createAt;
}
