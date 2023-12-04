package com.artpro.artpro.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DataDoesNotExistException extends RuntimeException {

    private final ErrorCode errorCode;
}