package com.artpro.artpro.board.entity;

import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.entity.Role;

public enum Category {

    ALL,
    ARTIST,
    PRODUCER;

    public static Category getCategoryByMemberRole(Member member) {
        if (member.getRole() == Role.ROLE_ARTIST) {
            return ARTIST;
        }
        return PRODUCER;
    }
}
