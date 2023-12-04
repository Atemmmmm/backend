package com.artpro.artpro.member.entity;

import io.jsonwebtoken.Claims;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String nickname;
    private String password;
    private Role role;

    @Builder
    public Member(Long id, String email, String password, String nickname, Role role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    public Member(Claims claims) {
        this.email = claims.get("email").toString();
    }
}
