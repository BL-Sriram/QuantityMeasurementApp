package com.example.quantity_measurement_app.service;

import com.example.quantity_measurement_app.dto.*;
import com.example.quantity_measurement_app.enums.*;
import com.example.quantity_measurement_app.interfaces.IMeasurable;
import com.example.quantity_measurement_app.model.QuantityMeasurementEntity;
import com.example.quantity_measurement_app.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    public QuantityMeasurementDTO convert(QuantityDTO dto) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        try {
            IMeasurable from = getUnit(dto.getMeasurementType(), dto.getFromUnit());
            IMeasurable to = getUnit(dto.getMeasurementType(), dto.getToUnit());

            double base = from.convertToBaseUnit(dto.getValue());
            double result = to.convertFromBaseUnit(base);

            entity.setOperation("CONVERT");
            entity.setMeasurementType(dto.getMeasurementType());
            entity.setInputValue(dto.getValue());
            entity.setResultValue(result);
            entity.setError(false);

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
        }

        repository.save(entity);
        return QuantityMeasurementDTO.fromEntity(entity);
    }

    private IMeasurable getUnit(String type, String unit) {
        return switch (type.toUpperCase()) {
            case "WEIGHT" -> WeightUnit.valueOf(unit.toUpperCase());
            case "LENGTH" -> LengthUnit.valueOf(unit.toUpperCase());
            case "VOLUME" -> VolumeUnit.valueOf(unit.toUpperCase());
            case "TEMPERATURE" -> TemperatureUnit.valueOf(unit.toUpperCase());
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }
}