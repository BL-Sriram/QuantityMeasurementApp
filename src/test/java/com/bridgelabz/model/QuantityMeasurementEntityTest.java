package com.bridgelabz.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementEntityTest {

    @Test
    void givenEntity_whenCreated_shouldStoreResult() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(20, "ADD");

        assertEquals(20, entity.resultValue);
        assertEquals("ADD", entity.operation);
    }

    @Test
    void givenErrorEntity_shouldMarkError() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("Error occurred");

        assertTrue(entity.isError);
    }
}