package com.artpro.artpro.chat.dto;

import com.artpro.artpro.member.dto.ProfileResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class ChattingListResponse {

    private final ProfileResponse counterpart;
    private final List<MessageResponse> messages;
}
