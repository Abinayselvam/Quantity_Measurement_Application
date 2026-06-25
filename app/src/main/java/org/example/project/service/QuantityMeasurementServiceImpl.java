package org.example.project.service;
import org.example.project.Interface.IMeasurable;
import org.example.project.dto.QuantityDTO;
import org.example.project.entities.QuantityMeasurementEntity;
import org.example.project.exception.QuantityMeasurementException;
import org.example.project.repository.IQuantityMeasurementRepository;
import static org.example.project.utils.QuantityMapper.getMeasurableUnit;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final
    IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {

        this.repository = repository;
    }

    @Override
    public boolean compare(
            QuantityDTO first,
            QuantityDTO second) {

        try {

            IMeasurable unit1 =
                     getMeasurableUnit(first);

            IMeasurable unit2 =
                    getMeasurableUnit(second);

            double value1 =
                    unit1.convertToBaseUnit(
                            first.getValue());

            double value2 =
                    unit2.convertToBaseUnit(
                            second.getValue());

            boolean result =
                    Math.abs(value1 - value2)
                            < 0.0001;

            repository.save(
                    new QuantityMeasurementEntity(
                            "COMPARE",
                            result + ""));

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    e.getMessage());
        }
    }

    @Override
    public QuantityDTO convert(
            QuantityDTO source,
            String targetUnit) {

        throw new UnsupportedOperationException(
                "Implement target unit mapping");
    }

    @Override
    public QuantityDTO add(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        IMeasurable unit1 =
                getMeasurableUnit(first);

        IMeasurable unit2 =
                getMeasurableUnit(second);

        if (!unit1.supportsArithmetic()) {

            unit1.validateOperationSupport(
                    "Addition");
        }

        double baseValue =
                unit1
                        .convertToBaseUnit(
                                first.getValue())
                        +
                        unit2
                                .convertToBaseUnit(
                                        second.getValue());

        throw new UnsupportedOperationException(
                "Implement target unit mapping");
    }

    @Override
    public QuantityDTO subtract(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {
        IMeasurable unit1 =
                getMeasurableUnit(first);

        IMeasurable unit2 =
                getMeasurableUnit(second);

        if (!unit1.supportsArithmetic()) {

            unit1.validateOperationSupport(
                    "Addition");
        }


        double baseValue =
               unit1
                        .convertToBaseUnit(
                                first.getValue())
                        -
                        unit2
                                .convertToBaseUnit(
                                        second.getValue());

        throw new UnsupportedOperationException(
                "Implement target unit mapping");
    }

    @Override
    public double divide(
            QuantityDTO first,
            QuantityDTO second) {
        IMeasurable unit1 =
                getMeasurableUnit(first);

        IMeasurable unit2 =
                getMeasurableUnit(second);

        if (!unit1.supportsArithmetic()) {

            unit1.validateOperationSupport(
                    "Addition");
        }

        double base1 =
                unit1
                        .convertToBaseUnit(
                                first.getValue());

        double base2 =
                unit2
                        .convertToBaseUnit(
                                second.getValue());

        if (base2 == 0) {

            throw new QuantityMeasurementException(
                    "Cannot divide by zero");
        }

        double result =
                base1 / base2;

        repository.save(
                new QuantityMeasurementEntity(
                        "DIVIDE",
                        String.valueOf(result)));

        return result;
    }

}