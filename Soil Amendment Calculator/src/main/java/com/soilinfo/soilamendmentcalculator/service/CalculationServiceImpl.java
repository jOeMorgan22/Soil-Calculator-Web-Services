package com.soilinfo.soilamendmentcalculator.service;

import com.soilinfo.soilamendmentcalculator.AmendmentConstants.SoilConstants;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService{

    @Override
    public String calculatePhAmendment(double soilPh) {
        double optimalDifference = SoilConstants.OPTIMUM_SOIL_PH - soilPh;
        String elementToAdd = optimalDifference > 0 ?  "Lime" : "Sulfur";
        double positiveDifference = optimalDifference > 0 ? optimalDifference : optimalDifference * -1;
        int lbsToAdd = elementToAdd.equals("Sulfur") ? 6 : 20;
        return String.format("Soil pH level is %s, recommend adding %.2f pounds of %s per 1000 sq. ft. ",
                soilPh, (lbsToAdd * (positiveDifference / 5)), elementToAdd);
    }

    @Override
    public String buildPResponse(int phosphorusPpm, double lbsOfPToAdd){
        return String.format("Phosphorus level is %d, recommend adding %.2f pounds of P2O5 per 1000 sq. ft. ",
                phosphorusPpm, lbsOfPToAdd);
    }

    @Override
    public String calculatePhosphorusAmendment(int phosphorusPpm) {
        int pDifference = phosphorusPpm - SoilConstants.OPTIMUM_PHOSPHORUS;
        if (pDifference >= 0){
            return "Phosphorus levels are adequate, no amendment needed. ";
        }else if(pDifference >= -19){
            return buildPResponse(phosphorusPpm, 1.5);
        }else if(pDifference >= -49) {
            return buildPResponse(phosphorusPpm, 4.0);
        }else{
            return buildPResponse(phosphorusPpm, 5.5);
        }
    }

    @Override
    public String buildKResponse(int potassiumPpm, double lbsOfKToAdd){
        return String.format("Potassium level is %d, recommend adding %.3f pounds of K2O per 1000 sq. ft. ",
                potassiumPpm, lbsOfKToAdd);
    }

    @Override
    public String calculatePotassiumAmendment(int potassiumPpm) {
        int kDifference = potassiumPpm - SoilConstants.OPTIMUM_POTASSIUM;
        if (kDifference >= 0){
            return "Potassium levels are adequate, no amendment needed. ";
        }else if(kDifference >= -149){
            return buildKResponse(potassiumPpm, 1.5);
        }else if(kDifference >= -249) {
            return buildKResponse(potassiumPpm, 2.75);
        }else{
            return buildKResponse(potassiumPpm, 5.75);
        }
    }

    @Override
    public String calculateCalciumAmendment(int calciumPpm) {
        if(calciumPpm <= 375){
            return String.format("Calcium level is %d, recommend adding 12 oz. of CaO3S2 per 100 sq. ft. ", calciumPpm);
        }
        return "Calcium levels are adequate, no amendment needed. ";
    }

    @Override
    public String calculateMagnesiumAmendment(int magnesiumPpm) {
        if(magnesiumPpm <= 40){
            return String.format("Magnesium level is %d, recommend adding 1.0 pound of MgO per 1000 sq. ft. ",
                    magnesiumPpm);
        }
        return "Magnesium Levels are adequate, no amendments needed. ";
    }

    @Override
    public String calculateZincAmendment(double zincPpm) {
        if(zincPpm <= 15){
            return String.format("Zinc level is %.2f, recommend adding 0.8 pounds of ZnSO4 per 1000 sq. ft. ", zincPpm);
        }
        return "Zinc levels are adequate, no amendments needed. ";
    }
}
