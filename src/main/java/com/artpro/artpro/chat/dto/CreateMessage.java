package com.artpro.artpro.chat.dto;

import com.artpro.artpro.chat.entity.Type;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMessage {

    private Long roomId;
    private String message;
    private String senderNickname;
    private String createAt;
    private Type type;
}
