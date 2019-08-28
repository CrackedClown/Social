package com.finalassignment.social.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TagListEmptyException extends Exception {
    public TagListEmptyException(String message){
        super(message);
    }
}
