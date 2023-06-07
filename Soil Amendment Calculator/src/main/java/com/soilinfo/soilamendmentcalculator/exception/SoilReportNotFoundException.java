package com.soilinfo.soilamendmentcalculator.exception;

public class SoilReportNotFoundException extends RuntimeException{

    public SoilReportNotFoundException(){
        super("Soil report not found.");
    }
}
