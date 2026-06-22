package org.example.project.Enums;

public enum LengthUnit {
    INCHES(1.0/12),
    FEET(1.0);
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
