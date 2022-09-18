package com.musala.drone.exceptionHandler;

import org.springframework.http.HttpStatus;

public abstract class GeneralException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public GeneralException(String message) {
        super(message);
    }

    protected abstract HttpStatus getHttpStatus();
}
