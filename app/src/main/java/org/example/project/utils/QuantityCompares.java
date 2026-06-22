package org.example.project.utils;

import org.example.project.entities.Feet;
import org.example.project.entities.Inch;

public class QuantityCompares {
    public static boolean feetCompares(double value1,double value2)
    {
        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);
        return feet1.equals(feet2);
    }

    public static boolean inchCompares(double value1,double value2)
    {
        Inch inch1 = new Inch(value1);
        Inch inch2 = new Inch(value2);
        return inch1.equals(inch2);
    }
}
