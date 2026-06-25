package org.example.project.utils;
import org.example.project.Interface.IMeasurable;
import org.example.project.model.QuantityModel;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(
            QuantityModel<U> q1,
            QuantityModel<U> q2) {

        System.out.println(q1 + " equals " + q2
                + " => " + q1.equals(q2));
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(
            QuantityModel<U> quantity,
            U targetUnit) {

        System.out.println(
                quantity + " => "
                        + quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(
            QuantityModel<U> q1,
            QuantityModel<U> q2,
            U targetUnit) {

        System.out.println(
                q1 + " + " + q2
                        + " = "
                        + q1.add(q2, targetUnit));
    }
    public static <U extends IMeasurable>
    void demonstrateSubtraction(
            QuantityModel<U> q1,
            QuantityModel<U> q2,
            U targetUnit) {

        System.out.println(
                q1.subtract(q2, targetUnit));
    }
    public static <U extends IMeasurable>
    void demonstrateDivision(
            QuantityModel<U> q1,
            QuantityModel<U> q2) {

        System.out.println(
                q1.divide(q2));
    }

}