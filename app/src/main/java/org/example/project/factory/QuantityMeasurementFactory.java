package org.example.project.factory;

import org.example.project.controller.QuantityMeasurementController;
import org.example.project.repository.IQuantityMeasurementRepository;
import org.example.project.repository.QuantityMeasurementCacheRepository;
import org.example.project.service.IQuantityMeasurementService;
import org.example.project.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementFactory {

    private QuantityMeasurementFactory() {
    }

    public static QuantityMeasurementController
    createController() {

        IQuantityMeasurementRepository repository =
                QuantityMeasurementCacheRepository
                        .getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(
                        repository);

        return new QuantityMeasurementController(
                service);
    }
}