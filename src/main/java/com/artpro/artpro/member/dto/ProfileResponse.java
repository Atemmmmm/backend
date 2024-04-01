package com.artpro.artpro.member.dto;

import com.artpro.artpro.member.entity.Member;

public record ProfileResponse(String nickname, String profileImage, String email, String role) {

    public ProfileResponse(Member member) {
        this(member.getNickname(), member.getProfileImage(), member.getEmail(), member.getRole().getProfile());
    }
}
