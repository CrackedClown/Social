package com.finalassignment.social.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IllegalModificationException extends Exception {
    public IllegalModificationException(String message){
        super(message);
    }
}
