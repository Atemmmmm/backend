package com.artpro.artpro.chat.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataDoesNotExistException;

public class DoesNotExistMemberException extends DataDoesNotExistException {

    public DoesNotExistMemberException() {
        super(ErrorCode.DOES_NOT_EXIST_MEMBER_IN_ROOM);
    }
}
