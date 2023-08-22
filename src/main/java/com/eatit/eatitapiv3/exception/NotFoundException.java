package com.eatit.eatitapiv3.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends EatItException {
    public NotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

}
