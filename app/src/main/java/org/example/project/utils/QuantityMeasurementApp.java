package org.example.project.utils;

import org.example.project.Enums.WeightUnit;
import org.example.project.Interface.IMeasurable;
import org.example.project.entities.QuantityWeight;
public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2) {

        System.out.println(q1 + " equals " + q2
                + " => " + q1.equals(q2));
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        System.out.println(
                quantity + " => "
                        + quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        System.out.println(
                q1 + " + " + q2
                        + " = "
                        + q1.add(q2, targetUnit));
    }
}