package com.artpro.artpro.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_ARTIST("ROLE_ARTIST", "Artist"),
    ROLE_PRODUCER("ROLE_PRODUCER", "Producer"),
    ROLE_ADMIN("ROLE_ADMIN", "Admin");

    private final String name;
    private final String profile;

    public static Role getRoleByName(String name) {
        return Arrays.stream(Role.values())
                .filter(r -> Objects.equals(r.name, name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}