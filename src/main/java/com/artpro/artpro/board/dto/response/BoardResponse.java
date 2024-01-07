package com.artpro.artpro.board.dto.response;

import com.artpro.artpro.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class BoardResponse {

    private final String title;
    private final String coverUrl;

    public BoardResponse(Board board) {
        this.title = board.getTitle();
        this.coverUrl = board.getCover();
    }
}
