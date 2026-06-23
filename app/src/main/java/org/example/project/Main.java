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

        System.out.println("===== UC6 Addition =====");

        switch (1) {

            case 1:

                QuantityLength feet =
                        new QuantityLength(1, LengthUnit.FEET);

                QuantityLength inches =
                        new QuantityLength(12, LengthUnit.INCHES);

                QuantityLength result =
                        feet.add(inches);

                System.out.println(
                        feet + " + " + inches +
                                " = " + result);

                break;

            case 2:

                QuantityLength yard =
                        new QuantityLength(1, LengthUnit.YARDS);

                QuantityLength feet2 =
                        new QuantityLength(3, LengthUnit.FEET);

                System.out.println(
                        yard.add(feet2));

                break;

            default:
                System.out.println("Invalid option");
        }

    }
}

