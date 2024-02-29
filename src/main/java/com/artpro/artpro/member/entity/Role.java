package com.artpro.artpro.member.entity;

import java.util.Arrays;
import java.util.Objects;

public enum Role {
    ARTIST("ARTIST"),
    PRODUCER("PRODUCER"),
    ADMIN("ADMIN");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public static Role getRole(String name) {
        return Arrays.stream(Role.values())
                .filter(r -> Objects.equals(r.name, name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}