package com.artpro.artpro.member.exception;

import com.artpro.artpro.global.DataExistException;
import com.artpro.artpro.global.ErrorCode;

public class ExistingNicknameException extends DataExistException {

    public ExistingNicknameException() {
        super(ErrorCode.EXITING_NICKNAME);
    }
}
