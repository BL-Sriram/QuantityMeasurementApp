package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.enums.*;
import com.bridgelabz.exception.QuantityMeasurementException;
import com.bridgelabz.interfaces.IMeasurable;
import com.bridgelabz.model.QuantityMeasurementEntity;
import com.bridgelabz.repository.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();

    private IMeasurable getUnit(String unit) {
        try {
            return LengthUnit.valueOf(unit);
        } catch (Exception e) {}
        try {
            return WeightUnit.valueOf(unit);
        } catch (Exception e) {}
        try {
            return VolumeUnit.valueOf(unit);
        } catch (Exception e) {}
        try {
            return TemperatureUnit.valueOf(unit);
        } catch (Exception e) {}

        throw new QuantityMeasurementException("Invalid unit");
    }

    public double add(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = getUnit(a.unit);
        IMeasurable unitB = getUnit(b.unit);

        if (!unitA.getMeasurementType().equals(unitB.getMeasurementType()))
            throw new QuantityMeasurementException("Different measurement types");

        double result = unitA.toBaseUnit(a.value) + unitB.toBaseUnit(b.value);

        repo.save(new QuantityMeasurementEntity(result, "ADD"));
        return result;
    }

    public boolean compare(QuantityDTO a, QuantityDTO b) {
        IMeasurable unitA = getUnit(a.unit);
        IMeasurable unitB = getUnit(b.unit);

        double valA = unitA.toBaseUnit(a.value);
        double valB = unitB.toBaseUnit(b.value);

        return valA == valB;
    }
}