package com.fargotest.core.exception;

public class BadRequestException extends Exception {
    
    public BadRequestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
