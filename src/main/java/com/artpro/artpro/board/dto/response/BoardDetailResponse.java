package com.artpro.artpro.board.dto.response;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.entity.Genre;
import lombok.Builder;

@Builder
public record BoardDetailResponse(Long id, String title, String nickname, String songUrl, int likeCount, Genre genre){
    public BoardDetailResponse(Board board) {
        this(board.getId(), board.getTitle(), board.getMember().getNickname(), board.getSong(), board.getLikeCount(), board.getGenre());
    }
}
