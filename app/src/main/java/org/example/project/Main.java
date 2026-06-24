package org.example.project;
import org.example.project.Enums.LengthUnit;
import org.example.project.Enums.VolumeUnit;
import org.example.project.Enums.WeightUnit;
import org.example.project.entities.Quantity;
import static org.example.project.utils.QuantityMeasurementApp.*;

public class Main {
    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);
        demonstrateConversion(
                length1,
                LengthUnit.INCHES);

        demonstrateAddition(
                length1,
                length2,
                LengthUnit.FEET);

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);

        demonstrateConversion(
                weight1,
                WeightUnit.GRAM);

        demonstrateAddition(
                weight1,
                weight2,
                WeightUnit.KILOGRAM);

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> milliLitre =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("=== Volume Equality ===");
        System.out.println(litre.equals(milliLitre));

        System.out.println("\n=== Volume Conversion ===");
        System.out.println(
                litre.convertTo(VolumeUnit.MILLILITRE));

        System.out.println(
                gallon.convertTo(VolumeUnit.LITRE));

        System.out.println("\n=== Volume Addition ===");
        System.out.println(
                litre.add(milliLitre));

        System.out.println(
                litre.add(gallon,
                        VolumeUnit.LITRE));


    }
}

