package com.artpro.artpro.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TokenResponse {

    private String grantType;
    private String accessToken;
    private String refreshToken;
}
