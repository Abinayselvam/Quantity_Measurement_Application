package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.Interface.IMeasurable;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.QuantityModel;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    /**
     * Converts a QuantityDTO received from the API into a QuantityModel<IMeasurable>
     * that the core business logic operates on.
     */
    private QuantityModel<IMeasurable> convertDtoToModel(QuantityDTO dto) {
        IMeasurable unit = dto.resolveUnit();
        return new QuantityModel<>(dto.getValue(), unit);
    }

    private QuantityMeasurementEntity newEntityShell(QuantityInputDTO input, String operation) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        QuantityDTO first = input.getThisQuantityDTO();
        QuantityDTO second = input.getThatQuantityDTO();

        entity.setThisValue(first != null ? first.getValue() : null);
        entity.setThisUnit(first != null ? first.getUnit() : null);
        entity.setThisMeasurementType(first != null ? first.getMeasurementType() : null);

        entity.setThatValue(second != null ? second.getValue() : null);
        entity.setThatUnit(second != null ? second.getUnit() : null);
        entity.setThatMeasurementType(second != null ? second.getMeasurementType() : null);

        entity.setOperation(operation);
        entity.setError(false);

        return entity;
    }

    private QuantityMeasurementDTO saveAndReturn(QuantityMeasurementEntity entity) {
        QuantityMeasurementEntity saved = repository.save(entity);
        return QuantityMeasurementDTO.fromEntity(saved);
    }

    private QuantityMeasurementDTO saveError(QuantityInputDTO input, String operation, String message) {
        QuantityMeasurementEntity entity = newEntityShell(input, operation);
        entity.setError(true);
        entity.setErrorMessage(message);
        return saveAndReturn(entity);
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {
        try {
            QuantityModel<IMeasurable> first = convertDtoToModel(input.getThisQuantityDTO());
            QuantityModel<IMeasurable> second = convertDtoToModel(input.getThatQuantityDTO());

            boolean result = first.isEqualTo(second);

            QuantityMeasurementEntity entity = newEntityShell(input, "compare");
            entity.setResultString(String.valueOf(result));
            entity.setResultValue(0.0);

            return saveAndReturn(entity);

        } catch (Exception e) {
            saveError(input, "compare", e.getMessage());
            throw new QuantityMeasurementException(e.getMessage(), e);
        }
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityInputDTO input) {
        try {
            QuantityModel<IMeasurable> source = convertDtoToModel(input.getThisQuantityDTO());
            IMeasurable targetUnit = input.getThatQuantityDTO().resolveUnit();

            QuantityModel<IMeasurable> converted = source.convertTo(targetUnit);

            QuantityMeasurementEntity entity = newEntityShell(input, "convert");
            entity.setResultValue(converted.getValue());

            return saveAndReturn(entity);

        } catch (Exception e) {
            saveError(input, "convert", e.getMessage());
            throw new QuantityMeasurementException(e.getMessage(), e);
        }
    }

    @Override
    public QuantityMeasurementDTO add(QuantityInputDTO input) {
        try {
            QuantityModel<IMeasurable> first = convertDtoToModel(input.getThisQuantityDTO());
            QuantityModel<IMeasurable> second = convertDtoToModel(input.getThatQuantityDTO());

            IMeasurable targetUnit = resolveTargetUnit(input, first.getUnit());

            QuantityModel<IMeasurable> result = first.add(second, targetUnit);

            QuantityMeasurementEntity entity = newEntityShell(input, "add");
            entity.setResultValue(result.getValue());
            entity.setResultUnit(targetUnit.toString());
            entity.setResultMeasurementType(targetUnit.getMeasurementType());

            return saveAndReturn(entity);

        } catch (Exception e) {
            saveError(input, "add", e.getMessage());
            throw new QuantityMeasurementException("add Error: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityInputDTO input) {
        try {
            QuantityModel<IMeasurable> first = convertDtoToModel(input.getThisQuantityDTO());
            QuantityModel<IMeasurable> second = convertDtoToModel(input.getThatQuantityDTO());

            IMeasurable targetUnit = resolveTargetUnit(input, first.getUnit());

            QuantityModel<IMeasurable> result = first.subtract(second, targetUnit);

            QuantityMeasurementEntity entity = newEntityShell(input, "subtract");
            entity.setResultValue(result.getValue());
            entity.setResultUnit(targetUnit.toString());
            entity.setResultMeasurementType(targetUnit.getMeasurementType());

            return saveAndReturn(entity);

        } catch (Exception e) {
            saveError(input, "subtract", e.getMessage());
            throw new QuantityMeasurementException("subtract Error: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityInputDTO input) {
        try {
            QuantityModel<IMeasurable> first = convertDtoToModel(input.getThisQuantityDTO());
            QuantityModel<IMeasurable> second = convertDtoToModel(input.getThatQuantityDTO());

            double result = first.divide(second);

            QuantityMeasurementEntity entity = newEntityShell(input, "divide");
            entity.setResultValue(result);

            return saveAndReturn(entity);

        } catch (ArithmeticException e) {
            // System-level exception (e.g. divide by zero) - do not persist as a
            // business "error" record; let global handler return 500.
            throw e;
        } catch (Exception e) {
            saveError(input, "divide", e.getMessage());
            throw new QuantityMeasurementException("divide Error: " + e.getMessage(), e);
        }
    }

    private IMeasurable resolveTargetUnit(QuantityInputDTO input, IMeasurable defaultUnit) {
        String targetUnitName = input.getTargetUnit();
        if (targetUnitName == null || targetUnitName.isBlank()) {
            return defaultUnit;
        }
        return defaultUnit.getUnitByName(targetUnitName);
    }

    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        List<QuantityMeasurementEntity> entities =
                repository.findByOperation(operation.toLowerCase());
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String measurementType) {
        List<QuantityMeasurementEntity> entities =
                repository.findByThisMeasurementType(measurementType);
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        List<QuantityMeasurementEntity> entities = repository.findByIsErrorTrue();
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toLowerCase());
    }
}
