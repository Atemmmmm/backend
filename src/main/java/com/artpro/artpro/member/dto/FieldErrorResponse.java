package com.artpro.artpro.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FieldErrorResponse {
    private final String field;
    private final String value;
    private final String reason;

    @Builder
    public FieldErrorResponse(String field, String value, String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }
}