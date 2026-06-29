package org.example.project.factory;

import org.example.project.controller.QuantityMeasurementController;
import org.example.project.repository.IQuantityMeasurementRepository;
import org.example.project.repository.QuantityMeasurementJDBCRepository;
import org.example.project.service.IQuantityMeasurementService;
import org.example.project.service.QuantityMeasurementServiceImpl;
import org.example.project.utils.DBInitializer;

public class QuantityMeasurementFactory {

    public static QuantityMeasurementController createController() {

        // Step 1 - Create Database
        DBInitializer.initialize();

        // Step 2 - Create Repository
        IQuantityMeasurementRepository repository =
                new QuantityMeasurementJDBCRepository();

        // Step 3 - Create Service
        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        // Step 4 - Create Controller
        return new QuantityMeasurementController(service);
    }
}