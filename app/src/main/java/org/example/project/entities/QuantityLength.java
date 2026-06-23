package org.example.project.entities;

import org.example.project.Enums.LengthUnit;

import java.util.Objects;
public class QuantityLength {

    private static final double EPSILON = 0.000001;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(convertedValue, targetUnit);
    }
    public QuantityLength add(QuantityLength other) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        double firstBase =
                this.value * this.unit.getConversionFactor();

        double secondBase =
                other.value * other.unit.getConversionFactor();

        double sumBase = firstBase + secondBase;

        double result =
                sumBase / this.unit.getConversionFactor();

        return new QuantityLength(result, this.unit);
    }
    public QuantityLength add(
            QuantityLength other,
            LengthUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException();

        if (targetUnit == null)
            throw new IllegalArgumentException();

        double totalBase =
                unit.convertToBaseUnit(value)
                        +
                        other.unit.convertToBaseUnit(other.value);

        double result =
                targetUnit.convertFromBaseUnit(totalBase);

        return new QuantityLength(result, targetUnit);
    }
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof QuantityLength))
            return false;

        QuantityLength other = (QuantityLength) obj;

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase)
                < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                convertToBaseUnit()
        );
    }

    @Override
    public String toString() {

        return String.format(
                "%.6f %s",
                value,
                unit
        );
    }
}