package com.soilinfo.soilamendmentcalculator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.isEmpty() || value.isBlank()){
            return false;
        }
        Pattern pattern = Pattern.compile("[^a-z0-9 ]");
        Matcher matcher = pattern.matcher(value);
        boolean badCharacters = matcher.find();
        return !badCharacters;
    }

}
