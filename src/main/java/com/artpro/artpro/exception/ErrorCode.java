package com.artpro.artpro.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INPUT_VALUE_INVALID(400, "입력값이 올바르지 않습니다.");

    private final int status;
    private final String message;
}
