package com.artpro.artpro.member.dto;

import com.artpro.artpro.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequest {

    private final String email;

    private final String nickname;

    private final String password;

    private final String checkPassword;

    private final Role role;
}
