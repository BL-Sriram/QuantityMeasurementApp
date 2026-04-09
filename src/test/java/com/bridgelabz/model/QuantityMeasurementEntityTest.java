package com.bridgelabz.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementEntityTest {

    @Test
    void givenEntity_whenCreated_shouldStoreValues() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(20, "FEET", "ADD");

        assertEquals(20, entity.getValue());
        assertEquals("FEET", entity.getUnit());
        assertEquals("ADD", entity.getOperation());
    }

    @Test
    void givenErrorEntity_shouldMarkError() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("Error occurred");

        assertTrue(entity.isError());
        assertEquals("Error occurred", entity.getErrorMessage());
    }
}