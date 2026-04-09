package com.bridgelabz.repository;

import com.bridgelabz.model.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAll();

    void deleteAll();

    int getTotalCount();
}