package com.quantity_measurement.quantity_service.service;

import com.quantity_measurement.quantity_service.dto.*;
import com.quantity_measurement.quantity_service.enums.*;
import com.quantity_measurement.quantity_service.interfaces.IMeasurable;
import com.quantity_measurement.quantity_service.model.QuantityMeasurementEntity;
import com.quantity_measurement.quantity_service.repository.QuantityMeasurementRepository;
import com.quantity_measurement.quantity_service.dto.QuantityDTO;
import com.quantity_measurement.quantity_service.dto.QuantityMeasurementDTO;
import com.quantity_measurement.quantity_service.enums.LengthUnit;
import com.quantity_measurement.quantity_service.enums.TemperatureUnit;
import com.quantity_measurement.quantity_service.enums.VolumeUnit;
import com.quantity_measurement.quantity_service.enums.WeightUnit;
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