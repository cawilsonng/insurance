package com.wilson.insurance.exceptions;

public class TargetNotFoundException extends Exception {
    public TargetNotFoundException() {
        super();
    }

    public TargetNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
