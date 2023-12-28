package com.artpro.artpro.auth.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataExistException;
import lombok.Getter;

@Getter
public class ExistingEmailException extends DataExistException {

    public ExistingEmailException() {
        super(ErrorCode.EXITING_EMAIL);
    }
}
