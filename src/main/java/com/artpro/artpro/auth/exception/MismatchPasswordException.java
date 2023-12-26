package com.artpro.artpro.auth.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataExistException;

public class MismatchPasswordException extends DataExistException {

    public MismatchPasswordException() {
        super(ErrorCode.MISMATCH_PASSWORD);
    }
}
