package com.example.quantity_measurement_app.repository;

import com.example.quantity_measurement_app.model.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAll();

    void deleteAll();

    int getTotalCount();
}