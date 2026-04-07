package com.bridgelabz.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityDTOTest {

    @Test
    void givenDTO_shouldStoreValues() {
        QuantityDTO dto = new QuantityDTO();
        dto.value = 5;
        dto.unit = "FEET";

        assertEquals(5, dto.value);
        assertEquals("FEET", dto.unit);
    }
}