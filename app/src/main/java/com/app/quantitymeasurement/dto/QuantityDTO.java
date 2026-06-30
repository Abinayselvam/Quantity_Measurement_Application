package com.app.quantitymeasurement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.app.quantitymeasurement.Enums.LengthUnit;
import com.app.quantitymeasurement.Enums.TemperatureUnit;
import com.app.quantitymeasurement.Enums.VolumeUnit;
import com.app.quantitymeasurement.Enums.WeightUnit;
import com.app.quantitymeasurement.Interface.IMeasurable;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityDTO {

    @NotNull(message = "Value cannot be null")
    private Double value;

    @NotEmpty(message = "Unit cannot be empty")
    private String unit;

    @NotEmpty(message = "Measurement type cannot be empty")
    @Pattern(
            regexp = "LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit",
            message = "measurementType must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit"
    )
    private String measurementType;

    @AssertTrue(message = "Unit must be valid for the specified measurement type")
    @JsonIgnore
    public boolean isUnitValid() {

        if (measurementType == null || unit == null) {
            return false;
        }

        try {
            resolveUnit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Resolves the IMeasurable unit instance represented by this DTO,
     * based on the measurementType and unit name fields.
     */
    @JsonIgnore
    public IMeasurable resolveUnit() {

        return switch (measurementType) {
            case "LengthUnit" -> LengthUnit.valueOf(unit.toUpperCase());
            case "WeightUnit" -> WeightUnit.valueOf(unit.toUpperCase());
            case "VolumeUnit" -> VolumeUnit.valueOf(unit.toUpperCase());
            case "TemperatureUnit" -> TemperatureUnit.valueOf(unit.toUpperCase());
            default -> throw new IllegalArgumentException(
                    "Unsupported measurement type: " + measurementType);
        };
    }
}
