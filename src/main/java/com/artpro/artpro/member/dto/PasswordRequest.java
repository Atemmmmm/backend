package com.artpro.artpro.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PasswordRequest {

    private final String password;
    private final String checkPassword;
}
