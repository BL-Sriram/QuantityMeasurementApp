package com.quantity_measurement.quantity_service.repository;

import com.quantity_measurement.quantity_service.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(String operation);
}