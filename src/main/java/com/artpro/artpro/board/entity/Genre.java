package com.artpro.artpro.board.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Genre {
    POP("POP"),
    ROCK("ROCK"),
    HIPHOP("RAP/HIPHOP"),
    INDIE("INDIE"),
    JAZZ("JAZZ"),
    DANCE("DANCE"),
    RNB("R&B"),
    BALLAD("BALLAD");

    private final String value;

    public static Genre getGenre(String value) {
        // TODO : 존재하지 않는 장르일 경우 예외 처리 기능 추가
        return Arrays.stream(Genre.values())
                .filter(genre -> value.equals(genre.value))
                .findAny()
                .orElseThrow();
    }
}
