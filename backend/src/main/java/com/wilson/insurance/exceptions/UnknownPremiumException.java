package com.wilson.insurance.exceptions;

public class UnknownPremiumException extends Exception {
    public UnknownPremiumException() {
        super();
    }

    public UnknownPremiumException(String errorMessage) {
        super(errorMessage);
    }
}
