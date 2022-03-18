package com.waracle.cakemgr.exception;

public class CakeNotFoundException extends RuntimeException {

    public CakeNotFoundException(final String message) {
        super(message);
    }
}
