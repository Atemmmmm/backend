package com.artpro.artpro.room.dto.response;

import com.artpro.artpro.chat.dto.MessageResponse;
import com.artpro.artpro.member.dto.ProfileResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class RoomResponse {

    private final Long roomId;
    private final ProfileResponse counterpart;
    private final MessageResponse lastMessage;
}
