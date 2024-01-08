package com.artpro.artpro.board.dto.response;

import com.artpro.artpro.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class BoardDetailResponse {

    private final String title;
    private final String nickname;
    private final String songUrl;

    public BoardDetailResponse(Board board) {
        this.title = board.getTitle();
        this.nickname = board.getMember().getNickname();
        this.songUrl = board.getSong();
    }
}
