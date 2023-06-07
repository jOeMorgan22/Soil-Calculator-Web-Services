package com.soilinfo.soilamendmentcalculator.responseobj;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public record SoilReportResponseDetails(Long id, String location, @JsonFormat(pattern = "dd-MM-yyyy") LocalDateTime date,
                                        double soilPh, Integer phosphorus, Integer potassium, Integer calcium,
                                        Integer magnesium, double zinc, String recommendations) {
}
