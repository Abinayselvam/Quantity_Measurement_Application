package org.example.project.utils;

import org.example.project.Enums.LengthUnit;
import org.example.project.entities.QuantityLength;

public  class QuantityCompares {
    public static boolean compareLength(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
        QuantityLength q1 = new QuantityLength(value1,unit1);
        QuantityLength q2 = new QuantityLength(value2,unit2);
        return q1.equals(q2);

    }
}