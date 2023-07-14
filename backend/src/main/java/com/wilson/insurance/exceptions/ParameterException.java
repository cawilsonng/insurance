package com.wilson.insurance.exceptions;

public class ParameterException extends Exception {
    public ParameterException() {
        super();
    }

    public ParameterException(String errorMessage) {
        super(errorMessage);
    }
}
