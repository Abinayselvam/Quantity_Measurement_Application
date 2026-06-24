package org.example.project.entities;
import org.example.project.Enums.ArithmeticOperation;
import org.example.project.Interface.IMeasurable;
import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 0.000001;
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null");
        }

        if (!unit.getClass()
                .equals(targetUnit.getClass())) {

            throw new IllegalArgumentException(
                    "Cannot convert between different measurement categories");
        }

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(
                round(convertedValue),
                targetUnit
        );
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (unit == null || other.unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException(
                    "Cannot perform arithmetic on different measurement categories");
        }

        if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException(
                    "Values must be finite numbers");
        }

        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null");
        }
    }
    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        unit.validateOperationSupport(
                operation.name());

        other.unit.validateOperationSupport(
                operation.name());

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(
                        other.value);

        return operation.compute(
                thisBase,
                otherBase);
    }
    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(
                other,
                unit,
                true);

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.ADD);

        double result =
                unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(
                round(result),
                unit);
    }
    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true);

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.ADD);

        double result =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(
                round(result),
                targetUnit);
    }
    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true);

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT);

        double result =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(
                round(result),
                targetUnit);
    }
    public double divide(
            Quantity<U> other) {

        validateArithmeticOperands(
                other,
                null,
                false);

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE);
    }
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quantity<?> other)) {
            return false;
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double thisBase =
                this.unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {

        double baseValue =
                unit.convertToBaseUnit(value);

        return Objects.hash(
                unit.getClass(),
                Math.round(baseValue / EPSILON)
        );
    }


    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}