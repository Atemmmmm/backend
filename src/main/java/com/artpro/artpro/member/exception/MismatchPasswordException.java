package com.artpro.artpro.member.exception;

import com.artpro.artpro.global.DataExistException;
import com.artpro.artpro.global.ErrorCode;

public class MismatchPasswordException extends DataExistException {

    public MismatchPasswordException() {
        super(ErrorCode.MISMATCH_PASSWORD);
    }
}
