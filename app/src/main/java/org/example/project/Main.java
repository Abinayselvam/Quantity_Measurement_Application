package org.example.project;

import org.example.project.Enums.*;
import org.example.project.entities.Quantity;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== QUANTITY MEASUREMENT SYSTEM =====");
            System.out.println("1. Length Operations");
            System.out.println("2. Weight Operations");
            System.out.println("3. Volume Operations");
            System.out.println("4. Temperature Operations");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    performLengthOperations();
                    break;

                case 2:
                    performWeightOperations();
                    break;

                case 3:
                    performVolumeOperations();
                    break;

                case 4:
                    performTemperatureOperations();
                    break;

                case 5:
                    System.out.println("Exiting Application...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    private static void performLengthOperations() {

        Quantity<LengthUnit> feet =
                new Quantity<>(2.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("\n=== LENGTH OPERATIONS ===");

        System.out.println("Equality: "
                + feet.equals(inches));

        System.out.println("Conversion: "
                + feet.convertTo(LengthUnit.INCHES));

        System.out.println("Addition: "
                + feet.add(inches));

        System.out.println("Subtraction: "
                + feet.subtract(inches,LengthUnit.INCHES));

        System.out.println("Division: "
                + feet.divide(inches));
    }

    private static void performWeightOperations() {

        Quantity<WeightUnit> kg =
                new Quantity<>(2.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("\n=== WEIGHT OPERATIONS ===");

        System.out.println("Equality: "
                + kg.equals(gram));

        System.out.println("Conversion: "
                + kg.convertTo(WeightUnit.GRAM));

        System.out.println("Addition: "
                + kg.add(gram));

        System.out.println("Subtraction: "
                + kg.subtract(gram,WeightUnit.GRAM));

        System.out.println("Division: "
                + kg.divide(gram));
    }

    private static void performVolumeOperations() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        System.out.println("\n=== VOLUME OPERATIONS ===");

        System.out.println("Equality: "
                + litre.equals(ml));

        System.out.println("Conversion: "
                + litre.convertTo(VolumeUnit.MILLILITRE));

        System.out.println("Addition: "
                + litre.add(ml));

        System.out.println("Subtraction: "
                + litre.subtract(ml,VolumeUnit.MILLILITRE));

        System.out.println("Division: "
                + litre.divide(ml));
    }

    private static void performTemperatureOperations() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0,
                        TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0,
                        TemperatureUnit.FAHRENHEIT);

        System.out.println("\n=== TEMPERATURE OPERATIONS ===");

        System.out.println("Equality: "
                + celsius.equals(fahrenheit));

        System.out.println("Conversion: "
                + celsius.convertTo(
                TemperatureUnit.FAHRENHEIT));

        try {

            System.out.println(
                    celsius.add(fahrenheit));

        } catch (Exception e) {

            System.out.println(
                    "Addition Not Supported : "
                            + e.getMessage());
        }

        try {

            System.out.println(
                    celsius.subtract(fahrenheit,TemperatureUnit.CELSIUS));

        } catch (Exception e) {

            System.out.println(
                    "Subtraction Not Supported : "
                            + e.getMessage());
        }

        try {

            System.out.println(
                    celsius.divide(fahrenheit));

        } catch (Exception e) {

            System.out.println(
                    "Division Not Supported : "
                            + e.getMessage());
        }
    }
}