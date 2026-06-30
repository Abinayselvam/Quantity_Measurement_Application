package com.app.quantitymeasurement.Enums;

/**
 * Represents the different types of operations that can be performed
 * in the Quantity Measurement application. Provides a type-safe way
 * to refer to operations across DTOs, services, and the repository layer.
 */
public enum OperationType {

    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    COMPARE,
    CONVERT;

    public static OperationType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Operation type cannot be null");
        }
        return OperationType.valueOf(value.trim().toUpperCase());
    }
}
