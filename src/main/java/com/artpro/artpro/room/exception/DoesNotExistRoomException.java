package com.artpro.artpro.room.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataDoesNotExistException;

public class DoesNotExistRoomException extends DataDoesNotExistException {

    public DoesNotExistRoomException() {
        super(ErrorCode.DOES_NOT_EXIST_ROOM);
    }
}
