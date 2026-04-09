package com.bridgelabz.repository;

import com.bridgelabz.model.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementRepositoryTest {

    @Test
    void givenEntity_whenSaved_shouldBeStored() {
        IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();

        repo.deleteAll();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(10, "FEET", "ADD");

        repo.save(entity);

        assertTrue(repo.getAll().size() > 0);
    }
}