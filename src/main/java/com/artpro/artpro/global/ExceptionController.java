package com.artpro.artpro.global;

import com.artpro.artpro.member.dto.ErrorResponse;
import com.artpro.artpro.member.dto.FieldErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldErrorResponse> fieldErrorResponses  = getFieldErrors(e.getBindingResult());
        return buildFieldErrors(ErrorCode.INVALID_INPUT_VALUE, fieldErrorResponses);
    }

    @ExceptionHandler(DataExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ExceptionResponse handleConflictException(DataExistException e){
        ErrorCode errorCode = e.getErrorCode();
        return ExceptionResponse.builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    @ExceptionHandler(DataDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ExceptionResponse handleNotFoundException(DataDoesNotExistException e){
        ErrorCode errorCode = e.getErrorCode();
        return ExceptionResponse.builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    private List<FieldErrorResponse> getFieldErrors(BindingResult bindingResult) {
        final List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.parallelStream()
                .map(error -> FieldErrorResponse.builder()
                        .reason(error.getDefaultMessage())
                        .field(error.getField())
                        .value((String) error.getRejectedValue())
                        .build())
                .toList();
    }

    private ErrorResponse buildFieldErrors(ErrorCode errorCode, List<FieldErrorResponse> errors) {
        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }
}
