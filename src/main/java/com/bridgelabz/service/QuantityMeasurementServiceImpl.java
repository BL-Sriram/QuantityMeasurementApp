package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.enums.*;
import com.bridgelabz.exception.QuantityMeasurementException;
import com.bridgelabz.interfaces.IMeasurable;
import com.bridgelabz.model.QuantityMeasurementEntity;
import com.bridgelabz.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private final IQuantityMeasurementRepository repo;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repo = repo;
    }

    private IMeasurable getUnit(String unit) {
        try { return LengthUnit.valueOf(unit); } catch (Exception ignored) {}
        try { return WeightUnit.valueOf(unit); } catch (Exception ignored) {}
        try { return VolumeUnit.valueOf(unit); } catch (Exception ignored) {}
        try { return TemperatureUnit.valueOf(unit); } catch (Exception ignored) {}

        throw new QuantityMeasurementException("Invalid unit");
    }

    @Override
    public double add(QuantityDTO a, QuantityDTO b) {

        try {
            IMeasurable unitA = getUnit(a.unit);
            IMeasurable unitB = getUnit(b.unit);

            if (!unitA.getMeasurementType().equals(unitB.getMeasurementType()))
                throw new QuantityMeasurementException("Different measurement types");

            double result = unitA.toBaseUnit(a.value) + unitB.toBaseUnit(b.value);

            repo.save(new QuantityMeasurementEntity(result, a.unit, "ADD"));

            return result;

        } catch (Exception e) {

            repo.save(new QuantityMeasurementEntity(e.getMessage()));
            throw e;
        }
    }

    @Override
    public boolean compare(QuantityDTO a, QuantityDTO b) {

        try {
            IMeasurable unitA = getUnit(a.unit);
            IMeasurable unitB = getUnit(b.unit);

            double valA = unitA.toBaseUnit(a.value);
            double valB = unitB.toBaseUnit(b.value);

            boolean result = valA == valB;

            repo.save(new QuantityMeasurementEntity(result ? 1 : 0, a.unit, "COMPARE"));

            return result;

        } catch (Exception e) {
            repo.save(new QuantityMeasurementEntity(e.getMessage()));
            throw e;
        }
    }
}