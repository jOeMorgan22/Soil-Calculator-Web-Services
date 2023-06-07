package com.soilinfo.soilamendmentcalculator.exception;

public class AppUserAlreadyExistsException extends RuntimeException{

    public AppUserAlreadyExistsException() {
        super("Username already exists");
    }
}
