package com.company.Summative1TriJonathanRichard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class LowInventoryException extends RuntimeException {

    public LowInventoryException() {
        super();
    }

    public LowInventoryException(String msg) {
        super(msg);
    }
}
