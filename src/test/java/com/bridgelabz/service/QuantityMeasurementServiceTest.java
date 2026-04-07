package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementServiceTest {

    private IQuantityMeasurementService service = new QuantityMeasurementServiceImpl();

    @Test
    void givenSameLengthUnits_whenCompared_shouldReturnTrue() {
        QuantityDTO q1 = new QuantityDTO();
        q1.value = 1;
        q1.unit = "FEET";

        QuantityDTO q2 = new QuantityDTO();
        q2.value = 12;
        q2.unit = "INCH";

        assertTrue(service.compare(q1, q2));
    }

    @Test
    void givenLengthUnits_whenAdded_shouldReturnSumInBaseUnit() {
        QuantityDTO q1 = new QuantityDTO();
        q1.value = 1;
        q1.unit = "FEET";

        QuantityDTO q2 = new QuantityDTO();
        q2.value = 12;
        q2.unit = "INCH";

        double result = service.add(q1, q2);

        assertEquals(24, result); // 12 + 12 inches
    }

    @Test
    void givenDifferentTypes_whenAdd_shouldThrowException() {
        QuantityDTO q1 = new QuantityDTO();
        q1.unit = "FEET";
        q1.value = 1;

        QuantityDTO q2 = new QuantityDTO();
        q2.unit = "KG";
        q2.value = 1;

        assertThrows(RuntimeException.class, () -> service.add(q1, q2));
    }
}