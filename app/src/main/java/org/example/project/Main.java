package org.example.project;

import org.example.project.entities.Feet;

public class Main {
    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println(feet1.equals(feet2));

    }
}
