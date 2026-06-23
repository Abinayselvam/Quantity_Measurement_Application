package org.example.project;
import org.example.project.Enums.LengthUnit;
import org.example.project.entities.QuantityLength;

import static org.example.project.utils.QuantityCompares.*;

public class Main {
    public static void main(String[] args) {

        demonstrateLengthConversion(
                1,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                3,
                LengthUnit.YARDS,
                LengthUnit.FEET
        );

        demonstrateLengthConversion(
                36,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                1,
                LengthUnit.YARDS,
                3,
                LengthUnit.FEET
        );

        QuantityLength feet =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12, LengthUnit.INCHES);

        QuantityLength result1 =
                feet.add(inches, LengthUnit.FEET);

        System.out.println(result1);

        QuantityLength result2 =
                feet.add(inches, LengthUnit.INCHES);

        System.out.println(result2);

        QuantityLength result3 =
                feet.add(inches, LengthUnit.YARDS);

        System.out.println(result3);
    }
}

