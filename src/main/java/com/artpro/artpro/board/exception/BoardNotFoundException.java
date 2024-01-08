package com.artpro.artpro.board.exception;

import com.artpro.artpro.global.ErrorCode;
import com.artpro.artpro.global.exception.DataDoesNotExistException;

public class BoardNotFoundException extends DataDoesNotExistException {

    public BoardNotFoundException() {
        super(ErrorCode.BOARD_DOES_NOT_EXIST);
    }
}
