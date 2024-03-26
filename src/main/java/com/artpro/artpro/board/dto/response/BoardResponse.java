package com.artpro.artpro.board.dto.response;

import com.artpro.artpro.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class BoardResponse {

    private final Long id;
    private final String title;
    private final String coverUrl;
    private final int likeCount;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.coverUrl = board.getCover();
        this.likeCount = board.getLikeCount();
    }
}
