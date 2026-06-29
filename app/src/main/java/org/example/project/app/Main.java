package org.example.project.app;
import org.example.project.Enums.*;
import org.example.project.controller.QuantityMeasurementController;
import org.example.project.dto.QuantityDTO;
import org.example.project.factory.QuantityMeasurementFactory;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        QuantityMeasurementController controller =
                QuantityMeasurementFactory
                        .createController();

        QuantityDTO length1 =
                new QuantityDTO(
                        1,
                        LengthUnit.FEET,
                        "LENGTH");

        QuantityDTO length2 =
                new QuantityDTO(
                        12,
                        LengthUnit.INCHES,
                        "LENGTH");

        controller.performEqualityCheck(
                length1,
                length2);
        QuantityDTO feet =
                new QuantityDTO(
                        1,
                        LengthUnit.FEET,
                        "LENGTH");

        QuantityDTO inches =
                new QuantityDTO(
                        12,
                        LengthUnit.INCHES,
                        "LENGTH");

        controller.performEqualityCheck(
                feet,
                inches);
        QuantityDTO celsius =
                new QuantityDTO(
                        0,
                        TemperatureUnit.CELSIUS,
                        "TEMPERATURE");

        QuantityDTO fahrenheit =
                new QuantityDTO(
                        32,
                        TemperatureUnit.FAHRENHEIT,
                        "TEMPERATURE");

        controller.performEqualityCheck(
                celsius,
                fahrenheit);

    }
}