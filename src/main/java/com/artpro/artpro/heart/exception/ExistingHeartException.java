package com.artpro.artpro.heart.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataExistException;

public class ExistingHeartException extends DataExistException {

    public ExistingHeartException() {
        super(ErrorCode.EXITING_HEART);
    }
}