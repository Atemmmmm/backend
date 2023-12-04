package com.artpro.artpro.member.mapper;

import com.artpro.artpro.member.dto.RegisterRequest;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    public Member mapToEntity(RegisterRequest dto, String password) {
        return Member.builder()
                .email(dto.getEmail())
                .password(password)
                .nickname(dto.getNickname())
                .role(dto.getRole())
                .build();
    }
}
