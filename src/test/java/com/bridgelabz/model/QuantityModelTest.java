package com.bridgelabz.model;

import com.bridgelabz.enums.LengthUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityModelTest {

    @Test
    void givenModel_shouldStoreValueAndUnit() {
        QuantityModel<LengthUnit> model = new QuantityModel<>();
        model.value = 10;
        model.unit = LengthUnit.FEET;

        assertEquals(10, model.value);
        assertEquals("FEET", model.unit.getUnitName());
    }
}