package com.quantity_measurement.quantity_service.repository;

import com.quantity_measurement.quantity_service.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAll();

    void deleteAll();

    int getTotalCount();
}