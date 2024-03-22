package com.artpro.artpro.board.dto.request;

import com.artpro.artpro.board.entity.Genre;
import lombok.Getter;

@Getter
public class BoardParameter {

    private final String category;
    private final String orderCriteria;
    private final Genre genre;

    public BoardParameter(String category, String orderCriteria, String genre) {
        this.category = category;
        this.orderCriteria = orderCriteria;
        this.genre = Genre.getGenre(genre);
    }
}
