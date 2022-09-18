package com.musala.drone.exceptionHandler;

import org.springframework.http.HttpStatus;

public class BusinessException extends GeneralException{

    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

    protected HttpStatus getHttpStatus() {

        return HttpStatus.NOT_ACCEPTABLE;
    }
}
