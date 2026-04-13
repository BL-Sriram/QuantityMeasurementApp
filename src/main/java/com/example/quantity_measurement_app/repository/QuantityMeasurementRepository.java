package com.example.quantity_measurement_app.repository;

import com.example.quantity_measurement_app.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(String operation);
}