package org.example.project.mapper;
import org.example.project.Enums.*;
import org.example.project.Interface.IMeasurable;
import org.example.project.dto.QuantityDTO;
import org.example.project.exception.QuantityMeasurementException;

public class QuantityMapper {
    public static IMeasurable getMeasurableUnit(
            QuantityDTO dto) {

        String type =
                dto.getMeasurementType()
                        .toUpperCase();

        String unit =
                dto.getUnit()
                        .toUpperCase();

        switch (type) {

            case "LENGTH":
                return LengthUnit.valueOf(unit);

            case "WEIGHT":
                return WeightUnit.valueOf(unit);

            case "VOLUME":
                return VolumeUnit.valueOf(unit);

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unit);

            default:
                throw new QuantityMeasurementException(
                        "Invalid measurement type");
        }
    }
}