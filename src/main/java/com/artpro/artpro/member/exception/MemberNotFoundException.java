package com.artpro.artpro.member.exception;

import com.artpro.artpro.global.DataDoesNotExistException;
import com.artpro.artpro.global.ErrorCode;

public class MemberNotFoundException extends DataDoesNotExistException {

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_DOES_NOT_EXIST);
    }
}
