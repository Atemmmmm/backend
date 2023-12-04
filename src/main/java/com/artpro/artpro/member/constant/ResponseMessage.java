package com.artpro.artpro.member.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {

    AVAILABLE_EMAIL("사용할 수 있는 이메일입니다."),
    AVAILABLE_NICKNAME("사용할 수 있는 닉네임입니다."),
    MATCH_PASSWORD("패스워드가 확인되었습니다.");

    private final String message;
}
