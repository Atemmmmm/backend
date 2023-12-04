package com.artpro.artpro.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {

    private final String message;
    private final String code;
    private final int status;
    private final List<FieldErrorResponse> errors;

    @Builder
    public ErrorResponse(String message, String code, int status, List<FieldErrorResponse> errors) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.errors = initErrors(errors);
    }

    private List<FieldErrorResponse> initErrors(List<FieldErrorResponse> errors) {
        if (errors == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(errors);
    }
}
