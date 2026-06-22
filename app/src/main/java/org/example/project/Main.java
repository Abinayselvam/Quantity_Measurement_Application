package org.example.project;

import org.example.project.entities.Feet;
import org.example.project.utils.QuantityCompares;

public class Main {
    public static void main(String[] args) {
        System.out.println("Feet Equality: "+ QuantityCompares.feetCompares(1.0,1.0));
        System.out.println("Inch Equality: "+ QuantityCompares.inchCompares(1.0,1.0));
    }
}
