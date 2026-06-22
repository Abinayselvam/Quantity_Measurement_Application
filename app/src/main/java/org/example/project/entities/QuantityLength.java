package org.example.project.entities;

import org.example.project.Enums.LengthUnit;

import java.util.Objects;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;
    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("LengthUnit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }
    public LengthUnit getUnit() {
        return unit;
    }
    private double convertToFeet()
    {
        return value*unit.getConversionFactor();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityLength other = (QuantityLength) o;
        return Double.compare(this.convertToFeet(),other.convertToFeet()) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(convertToFeet());
    }
}
