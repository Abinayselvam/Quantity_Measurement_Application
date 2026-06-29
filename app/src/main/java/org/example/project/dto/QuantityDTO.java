package org.example.project.dto;

import org.example.project.Interface.IMeasurable;

public class QuantityDTO {

    private double value;
    private IMeasurable unit;
    private String measurementType;

    public QuantityDTO() {
    }

    public QuantityDTO(
            double value,
           IMeasurable  unit,
            String measurementType) {

        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(IMeasurable unit) {
        this.unit = unit;
    }

    public void setMeasurementType(
            String measurementType) {

        this.measurementType = measurementType;
    }
}