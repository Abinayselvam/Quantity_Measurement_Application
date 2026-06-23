package org.example.project.utils;

import org.example.project.Enums.LengthUnit;
import org.example.project.entities.QuantityLength;

public  class QuantityCompares {
    public static boolean compareLength(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
        QuantityLength q1 = new QuantityLength(value1,unit1);
        QuantityLength q2 = new QuantityLength(value2,unit2);
        return q1.equals(q2);
    }
    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target) {

        QuantityLength length =
                new QuantityLength(
                        value,
                        source
                );

        return length
                .convertTo(target)
                .getValue();
    }
    public static void demonstrateLengthConversion(
            double value,
            LengthUnit source,
            LengthUnit target) {

        double result =
                convert(
                        value,
                        source,
                        target
                );

        System.out.println(
                value + " " + source +
                        " = " +
                        result + " " +
                        target
        );
    }
    public static void demonstrateLengthEquality(
            QuantityLength q1,
            QuantityLength q2) {

        System.out.println(
                q1 + " == " +
                        q2 + " ? "
                        + q1.equals(q2)
        );
    }
    public static void demonstrateLengthConversion(
            QuantityLength quantity,
            LengthUnit targetUnit) {

        QuantityLength result =
                quantity.convertTo(
                        targetUnit
                );

        System.out.println(
                quantity +
                        " = " +
                        result
        );
    }
    public static void demonstrateLengthComparison(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2) {

        QuantityLength q1 =
                new QuantityLength(
                        value1,
                        unit1
                );

        QuantityLength q2 =
                new QuantityLength(
                        value2,
                        unit2
                );

        demonstrateLengthEquality(
                q1,
                q2
        );
    }
}