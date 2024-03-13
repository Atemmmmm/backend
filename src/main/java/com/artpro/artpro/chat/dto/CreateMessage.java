package com.artpro.artpro.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMessage {

    private Long roomId;
    private String message;
    private String senderNickname;
    private String createAt;
}
