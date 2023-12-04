package com.artpro.artpro.member.exception;

import com.artpro.artpro.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ExistingMemberException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "이미 사용중인 이메일입니다.";
    private final ErrorCode errorCode;

    public ExistingMemberException() {
        super(EXCEPTION_MESSAGE);
        errorCode = ErrorCode.EXITING_EMAIL;
    }
}
