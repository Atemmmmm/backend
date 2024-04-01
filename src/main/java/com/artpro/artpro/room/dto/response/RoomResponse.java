package com.artpro.artpro.room.dto.response;

import com.artpro.artpro.chat.dto.MessageResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class RoomResponse {

    private final String counterpartNickname;
    private final String counterpartEmail;
    private final MessageResponse lastMessage;
}
