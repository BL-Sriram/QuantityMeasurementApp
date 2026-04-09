package com.bridgelabz.controller;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.repository.IQuantityMeasurementRepository;
import com.bridgelabz.repository.QuantityMeasurementCacheRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementControllerTest {

    private IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();
    private QuantityMeasurementController controller = new QuantityMeasurementController(repo);

    @Test
    void givenQuantities_whenCompared_shouldReturnTrue() {
        QuantityDTO q1 = new QuantityDTO();
        q1.value = 1;
        q1.unit = "FEET";

        QuantityDTO q2 = new QuantityDTO();
        q2.value = 12;
        q2.unit = "INCH";

        assertTrue(controller.compare(q1, q2));
    }

    @Test
    void givenQuantities_whenAdded_shouldReturnCorrectSum() {
        QuantityDTO q1 = new QuantityDTO();
        q1.value = 2;
        q1.unit = "FEET";

        QuantityDTO q2 = new QuantityDTO();
        q2.value = 24;
        q2.unit = "INCH";

        assertEquals(48, controller.add(q1, q2));
    }
}