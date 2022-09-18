package com.musala.drone.exceptionHandler;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GeneralException{

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

    protected HttpStatus getHttpStatus() {

        return HttpStatus.NOT_FOUND;
    }
}
