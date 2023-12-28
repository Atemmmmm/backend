package com.artpro.artpro.auth.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataExistException;

public class ExistingNicknameException extends DataExistException {

    public ExistingNicknameException() {
        super(ErrorCode.EXITING_NICKNAME);
    }
}
