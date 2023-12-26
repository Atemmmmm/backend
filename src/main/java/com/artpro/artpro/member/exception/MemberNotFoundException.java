package com.artpro.artpro.member.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataDoesNotExistException;

public class MemberNotFoundException extends DataDoesNotExistException {

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_DOES_NOT_EXIST);
    }
}
