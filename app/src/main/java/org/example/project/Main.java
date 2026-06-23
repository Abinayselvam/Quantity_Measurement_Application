package org.example.project;
import org.example.project.Enums.LengthUnit;
import org.example.project.entities.QuantityLength;

import static org.example.project.utils.QuantityCompares.compareLength;

public class Main {
    public static void main(String[] args) {
        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength inch =
                new QuantityLength(36.0, LengthUnit.INCHES);

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength inchFromCm =
                new QuantityLength(0.393701, LengthUnit.INCHES);

        System.out.println(
                "1 Yard = 3 Feet : "
                        + yard.equals(feet));

        System.out.println(
                "1 Yard = 36 Inches : "
                        + yard.equals(inch));

        System.out.println(
                "1 cm = 0.393701 inch : "
                        + cm.equals(inchFromCm));
    }
}
