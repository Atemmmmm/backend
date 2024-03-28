package com.artpro.artpro.board.dto.request;

import com.artpro.artpro.board.entity.Category;
import com.artpro.artpro.board.entity.Genre;
import lombok.Getter;

@Getter
public class BoardParameter {

    private final Category category;

    private final String orderBy;

    private final Genre genre;

    public BoardParameter(Category category, String orderBy, String genre) {
        this.category = getCategory(category);
        this.orderBy = getOrderBy(orderBy);
        this.genre = getGenre(genre);
    }

    private Category getCategory(Category category) {
        if (category == null) {
            return Category.ALL;
        }
        return category;
    }

    private String getOrderBy(String orderBy) {
        if (orderBy == null) {
            return "likeCount";
        }
        return orderBy;
    }

    private Genre getGenre(String genre) {
        if (category == null) {
            return Genre.BALLAD;
        }
        return Genre.getGenre(genre);
    }
}
