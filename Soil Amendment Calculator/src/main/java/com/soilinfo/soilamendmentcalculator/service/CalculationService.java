package com.soilinfo.soilamendmentcalculator.service;

public interface CalculationService {

    String calculatePhAmendment(double soilPh);
    String calculatePhosphorusAmendment(int phosphorusPpm);
    String calculatePotassiumAmendment(int potassiumPpm);
    String calculateCalciumAmendment(int calciumPpm);
    String calculateMagnesiumAmendment(int magnesiumPpm);
    String calculateZincAmendment(double zincPpm);
    String buildPResponse(int phosphorusPpm, double lbsOfPToAdd);
    String buildKResponse(int potassiumPpm, double lbsOfKToAdd);
}
