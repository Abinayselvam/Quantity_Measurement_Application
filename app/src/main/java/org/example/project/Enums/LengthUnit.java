package org.example.project.Enums;
import org.example.project.Interface.IMeasurable;


public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getMeasurementType() {
        return "LENGTH";
    }

    @Override
    public IMeasurable getUnitByName(String unitName) {
        return LengthUnit.valueOf(unitName.toUpperCase());
    }
}