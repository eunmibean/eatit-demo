package com.eatit.eatitapiv3.exception;

import org.springframework.http.HttpStatus;

public class DuplicateException extends EatItException{
    public DuplicateException(String reason){
        super(HttpStatus.CONFLICT, reason);
    }

}

