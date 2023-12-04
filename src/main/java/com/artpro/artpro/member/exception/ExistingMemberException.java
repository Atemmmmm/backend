package com.artpro.artpro.member.exception;

import com.artpro.artpro.global.DataExistException;
import com.artpro.artpro.global.ErrorCode;
import lombok.Getter;

@Getter
public class ExistingMemberException extends DataExistException {

    public ExistingMemberException() {
        super(ErrorCode.EXITING_EMAIL);
    }
}
