package com.finalassignment.social.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String message;

    public ExceptionResponse(String message) {
        super();
        this.message = message;
    }
}