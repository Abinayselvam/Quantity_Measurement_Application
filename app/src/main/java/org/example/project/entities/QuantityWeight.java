package org.example.project.entities;
import org.example.project.Enums.WeightUnit;

import java.util.Objects;
import java.util.Objects;

public final class QuantityWeight {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid weight value");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = toBaseUnit();
        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityWeight(convertedValue, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(
            QuantityWeight other,
            WeightUnit targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double totalBase =
                this.toBaseUnit() + other.toBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(totalBase);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityWeight other = (QuantityWeight) obj;

        return Math.abs(
                this.toBaseUnit() - other.toBaseUnit())
                < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                Math.round(toBaseUnit() / EPSILON));
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}