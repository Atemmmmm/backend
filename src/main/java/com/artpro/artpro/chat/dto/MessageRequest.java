package com.artpro.artpro.chat.dto;

import lombok.Getter;

@Getter
public class MessageRequest {

    private String message;
    private String createAt;
    private String senderNickname;
}
