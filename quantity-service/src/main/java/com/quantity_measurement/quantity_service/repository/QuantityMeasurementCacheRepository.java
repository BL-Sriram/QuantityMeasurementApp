package com.quantity_measurement.quantity_service.repository;

import com.quantity_measurement.quantity_service.model.QuantityMeasurementEntity;

import java.util.*;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;
    private List<QuantityMeasurementEntity> storage = new ArrayList<>();

    private QuantityMeasurementCacheRepository() {}

    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null)
            instance = new QuantityMeasurementCacheRepository();
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        storage.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAll() {
        return storage;
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

    @Override
    public int getTotalCount() {
        return storage.size();
    }
}