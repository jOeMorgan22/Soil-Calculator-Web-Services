package com.soilinfo.soilamendmentcalculator.exception;

public class AppUserNotFoundException extends RuntimeException{

    public AppUserNotFoundException(String username) {
        super(String.format("User '%s' not found", username));
    }

    public AppUserNotFoundException(Long id) {
        super(String.format("User '%d' not found", id));
    }
}
