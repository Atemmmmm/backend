package com.artpro.artpro.member.entity;

import com.artpro.artpro.heart.entity.Heart;
import io.jsonwebtoken.Claims;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    private String profileImage;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany
    private Set<Heart> hearts;

    @Builder
    public Member(Long id, String email, String password, String nickname, Role role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.hearts = new HashSet<>();
    }

    public Member(Claims claims) {
        this.id = Long.parseLong(claims.get("id").toString());
        this.email = claims.getSubject();
        this.role = Role.getRoleByName(claims.get("auth").toString());
    }

    public void updateProfileImage(String url) {
        this.profileImage = url;
    }

    public boolean isSameMember(Member member) {
        return member.email.equals(this.email);
    }
}