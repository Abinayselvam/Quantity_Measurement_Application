package org.example.project.utils;

import org.example.project.Enums.WeightUnit;
import org.example.project.entities.QuantityWeight;

public class QuantityMeasurementHelper {
    public static QuantityWeight demonstrateWeightConversion(
            double value,
            WeightUnit source,
            WeightUnit target) {

        return new QuantityWeight(value, source)
                .convertTo(target);
    }

    public static boolean demonstrateWeightEquality(
            QuantityWeight w1,
            QuantityWeight w2) {

        return w1.equals(w2);
    }

    public static QuantityWeight demonstrateWeightAddition(
            QuantityWeight w1,
            QuantityWeight w2) {

        return w1.add(w2);
    }

    public static QuantityWeight demonstrateWeightAddition(
            QuantityWeight w1,
            QuantityWeight w2,
            WeightUnit targetUnit) {

        return w1.add(w2, targetUnit);
    }
}
