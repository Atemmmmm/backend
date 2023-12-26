package com.artpro.artpro.global.exception;

import com.artpro.artpro.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DataExistException extends RuntimeException {

    private final ErrorCode errorCode;
}
