package com.artpro.artpro.auth.mapper;

import com.artpro.artpro.auth.dto.RegisterRequest;
import com.artpro.artpro.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public Member mapToEntity(RegisterRequest dto, String password) {
        return Member.builder()
                .email(dto.getEmail())
                .password(password)
                .nickname(dto.getNickname())
                .role(dto.getRole())
                .build();
    }
}
