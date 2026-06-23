package org.example.project.entities;

import org.example.project.Enums.LengthUnit;

import java.util.Objects;
public class QuantityLength {

    private static final double EPSILON = 0.000001;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Value must be finite"
            );
        }

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
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

    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    public QuantityLength convertTo(
            LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double baseValue =
                convertToBaseUnit();

        double convertedValue =
                baseValue /
                        targetUnit.getConversionFactor();

        return new QuantityLength(
                convertedValue,
                targetUnit
        );
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null ||
                getClass() != obj.getClass())
            return false;

        QuantityLength other =
                (QuantityLength) obj;

        return Math.abs(
                this.convertToBaseUnit()
                        -
                        other.convertToBaseUnit()
        ) < EPSILON;
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