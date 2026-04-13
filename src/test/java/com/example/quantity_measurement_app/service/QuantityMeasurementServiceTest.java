package com.example.quantity_measurement_app.service;

import com.example.quantity_measurement_app.dto.QuantityDTO;
import com.example.quantity_measurement_app.repository.QuantityMeasurementRepository;

import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementServiceTest {

    @Mock
    private QuantityMeasurementRepository repository;

    @InjectMocks
    private QuantityMeasurementServiceImpl service;

    public QuantityMeasurementServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertWeight() {

        QuantityDTO dto = new QuantityDTO();
        dto.setValue(1.0);
        dto.setFromUnit("KILOGRAM");
        dto.setToUnit("GRAM");
        dto.setMeasurementType("WEIGHT");

        var result = service.convert(dto);

        assertEquals(1000.0, result.getResultValue());
        assertFalse(result.isError());
    }
}