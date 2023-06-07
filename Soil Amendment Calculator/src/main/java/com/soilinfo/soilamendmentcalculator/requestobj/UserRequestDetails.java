package com.soilinfo.soilamendmentcalculator.requestobj;

import com.soilinfo.soilamendmentcalculator.validation.Password;
import com.soilinfo.soilamendmentcalculator.validation.Username;

public record UserRequestDetails(@Username String username, @Password String password) {
}
