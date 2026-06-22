package org.example.project;
import org.example.project.Enums.LengthUnit;

import static org.example.project.utils.QuantityCompares.compareLength;

public class Main {
    public static void main(String[] args) {
        System.out.println("1 Feet == 12 inches"+compareLength(1, LengthUnit.FEET,12,LengthUnit.INCHES));
        System.out.println("1 Inch == 1 Inch"+compareLength(1,LengthUnit.INCHES,1,LengthUnit.INCHES));
    }
}
