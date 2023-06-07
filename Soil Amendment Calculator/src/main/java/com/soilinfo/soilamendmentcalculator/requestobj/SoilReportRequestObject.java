package com.soilinfo.soilamendmentcalculator.requestobj;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record SoilReportRequestObject(

        @NotNull(message = "Location cannot be null")
        @NotEmpty(message = "Location cannot be empty")
        @NotBlank(message = "Location cannot be Blank")
        @Length(max = 100)
        String location,

        @DecimalMax(value = "9.0", message = "Soil pH may not be above 9.0")
        @DecimalMin(value = "3.5", message = "Soil pH may not be below 3.5")
        double soilPh,

        @Min(value = 1, message = "Phosphorus levels may not be 0")
        Integer phosphorus,

        @Min(value = 1, message = "Potassium levels may not be 0")
        Integer potassium,

        @Min(value = 1, message = "Calcium levels may not be 0")
        Integer calcium,

        @Min(value = 1, message = "Calcium levels may not be 0")
        Integer magnesium,

        @DecimalMin(value = ".5", message = "Zinc may not be below 0.5")
        double zinc) {
}
