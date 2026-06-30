package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurements", description = "REST API for quantity measurement operations")
public class QuantityMeasurementController {

    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementController.class);

    @Autowired
    private IQuantityMeasurementService service;

    @Operation(summary = "Compare two quantities", description = "Compares thisQuantityDTO and thatQuantityDTO for equality after unit normalization")
    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> compareQuantities(@Valid @RequestBody QuantityInputDTO input) {
        try {
            QuantityMeasurementDTO result = service.compare(input);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error comparing quantities: {}", e.getMessage());
            throw e;
        }
    }

    @Operation(summary = "Convert a quantity", description = "Converts thisQuantityDTO into the unit specified by thatQuantityDTO")
    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> convertQuantities(@Valid @RequestBody QuantityInputDTO input) {
        try {
            QuantityMeasurementDTO result = service.convert(input);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error converting quantity: {}", e.getMessage());
            throw e;
        }
    }

    @Operation(summary = "Add two quantities", description = "Adds thisQuantityDTO and thatQuantityDTO, optionally into targetUnit")
    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> addQuantities(@Valid @RequestBody QuantityInputDTO input) {
        try {
            QuantityMeasurementDTO result = service.add(input);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error adding quantities: {}", e.getMessage());
            throw e;
        }
    }

    @Operation(summary = "Subtract two quantities", description = "Subtracts thatQuantityDTO from thisQuantityDTO, optionally into targetUnit")
    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO> subtractQuantities(@Valid @RequestBody QuantityInputDTO input) {
        try {
            QuantityMeasurementDTO result = service.subtract(input);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error subtracting quantities: {}", e.getMessage());
            throw e;
        }
    }

    @Operation(summary = "Divide two quantities", description = "Divides thisQuantityDTO by thatQuantityDTO (base-unit ratio)")
    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO> divideQuantities(@Valid @RequestBody QuantityInputDTO input) {
        try {
            QuantityMeasurementDTO result = service.divide(input);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error dividing quantities: {}", e.getMessage());
            throw e;
        }
    }

    @Operation(summary = "Get operation history", description = "Retrieves all quantity measurement records for the given operation type")
    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(@PathVariable String operation) {
        return ResponseEntity.ok(service.getOperationHistory(operation));
    }

    @Operation(summary = "Get measurements by type", description = "Retrieves all quantity measurement records for the given measurement type")
    @GetMapping("/history/type/{measurementType}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getMeasurementsByType(@PathVariable String measurementType) {
        return ResponseEntity.ok(service.getMeasurementsByType(measurementType));
    }

    @Operation(summary = "Get error history", description = "Retrieves all quantity measurement records that resulted in an error")
    @GetMapping("/history/errored")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory() {
        return ResponseEntity.ok(service.getErrorHistory());
    }

    @Operation(summary = "Get operation count", description = "Retrieves the count of successful operations for the given operation type")
    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getOperationCount(@PathVariable String operation) {
        return ResponseEntity.ok(service.getOperationCount(operation));
    }
}
