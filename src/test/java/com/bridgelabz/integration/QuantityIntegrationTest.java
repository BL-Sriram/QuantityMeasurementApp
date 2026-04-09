package com.bridgelabz.integration;

import com.bridgelabz.controller.QuantityMeasurementController;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.repository.IQuantityMeasurementRepository;
import com.bridgelabz.repository.QuantityMeasurementDatabaseRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityIntegrationTest {

    @Test
    void givenFullFlow_whenAdd_shouldWorkEndToEnd() {

        IQuantityMeasurementRepository repo = new QuantityMeasurementDatabaseRepository();
        QuantityMeasurementController controller = new QuantityMeasurementController(repo);

        QuantityDTO q1 = new QuantityDTO();
        q1.value = 1;
        q1.unit = "FEET";

        QuantityDTO q2 = new QuantityDTO();
        q2.value = 12;
        q2.unit = "INCH";

        double result = controller.add(q1, q2);

        assertEquals(24, result);
    }
}