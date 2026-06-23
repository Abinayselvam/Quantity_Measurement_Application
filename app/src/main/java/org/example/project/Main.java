package org.example.project;
import org.example.project.Enums.LengthUnit;
import org.example.project.Enums.WeightUnit;
import org.example.project.entities.QuantityLength;
import org.example.project.utils.Quantity;
import static org.example.project.utils.QuantityMeasurementApp.*;

public class Main {
    public static void main(String[] args) {

        QuantityLength feet =
                new QuantityLength(
                        1,
                        LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(
                        12,
                        LengthUnit.INCHES);

        System.out.println(
                feet.convertTo(
                        LengthUnit.INCHES));

        System.out.println(
                feet.add(
                        inches,
                        LengthUnit.FEET));

        System.out.println(
                new QuantityLength(
                        36,
                        LengthUnit.INCHES)
                        .equals(
                                new QuantityLength(
                                        1,
                                        LengthUnit.YARDS)));

        Quantity<LengthUnit> length1 =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);

        demonstrateConversion(
                length1,
                LengthUnit.INCHES);

        demonstrateAddition(
                length1,
                length2,
                LengthUnit.FEET);

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);

        demonstrateConversion(
                weight1,
                WeightUnit.GRAM);

        demonstrateAddition(
                weight1,
                weight2,
                WeightUnit.KILOGRAM);


    }
}

