package com.artpro.artpro.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum Role {
    ROLE_ARTIST("ROLE_ARTIST"),
    ROLE_PRODUCER("ROLE_PRODUCER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    public static Role getRoleByName(String name) {
        return Arrays.stream(Role.values())
                .filter(r -> Objects.equals(r.name, name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}