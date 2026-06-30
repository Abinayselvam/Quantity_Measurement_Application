package org.example.project.entities;


import java.io.Serializable;

public class QuantityMeasurementEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String operation;
    private final String operand1;
    private final String operand2;
    private final String result;
    private final boolean error;
    private final String errorMessage;

    // Success operation
    public QuantityMeasurementEntity(
            String operation,
            String operand1,
            String operand2,
            String result) {

        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.error = false;
        this.errorMessage = null;
    }
    public QuantityMeasurementEntity(
            String operation,
            String operand1,
            String result) {

        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = null;
        this.result = result;
        this.error = false;
        this.errorMessage = null;
    }

    // Error operation
    public QuantityMeasurementEntity(
            String operation,
            String errorMessage) {

        this.operation = operation;
        this.operand1 = null;
        this.operand2 = null;
        this.result = null;
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public String getOperation() {
        return operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getResult() {
        return result;
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {

        if (error) {
            return operation +
                    " FAILED : " +
                    errorMessage;
        }

        return operation +
                " | " +
                operand1 +
                " | " +
                operand2 +
                " => " +
                result;
    }
}