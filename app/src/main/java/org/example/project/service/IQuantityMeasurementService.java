package org.example.project.service;
import org.example.project.dto.QuantityDTO;

public interface IQuantityMeasurementService {

    boolean compare(
            QuantityDTO first,
            QuantityDTO second);

    QuantityDTO convert(
            QuantityDTO source,
            String targetUnit);

    QuantityDTO add(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit);

    QuantityDTO subtract(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit);

    double divide(
            QuantityDTO first,
            QuantityDTO second);
}
