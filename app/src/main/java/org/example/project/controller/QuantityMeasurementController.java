package org.example.project.controller;

import org.example.project.dto.QuantityDTO;
import org.example.project.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final
    IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service) {

        this.service = service;
    }

    public void performEqualityCheck(
            QuantityDTO first,
            QuantityDTO second) {

        boolean result =
                service.compare(first, second);

        System.out.println(
                "Equality Result : " + result);
    }

    public void performConversion(
            QuantityDTO source,
            String targetUnit) {

        System.out.println(
                service.convert(
                        source,
                        targetUnit));
    }

    public void performAddition(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        System.out.println(
                service.add(
                        first,
                        second,
                        targetUnit));
    }

    public void performSubtraction(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        System.out.println(
                service.subtract(
                        first,
                        second,
                        targetUnit));
    }

    public void performDivision(
            QuantityDTO first,
            QuantityDTO second) {

        System.out.println(
                service.divide(
                        first,
                        second));
    }
}