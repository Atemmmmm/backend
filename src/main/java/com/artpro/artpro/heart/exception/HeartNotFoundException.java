package com.artpro.artpro.heart.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataDoesNotExistException;

public class HeartNotFoundException extends DataDoesNotExistException {

    public HeartNotFoundException() {
        super(ErrorCode.HEART_DOES_NOT_EXIST);
    }
}
