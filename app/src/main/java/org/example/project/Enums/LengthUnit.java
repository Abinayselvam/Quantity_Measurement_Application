package org.example.project.Enums;

public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0/12),
    YARDS(3.0),
    CENTIMETERS(0.393701/12);
    private final double conversionFactor;
     LengthUnit(double conversionFactor)
    {
        this.conversionFactor = conversionFactor;
    }
    public double getConversionFactor()
    {
        return conversionFactor;
    }
}
