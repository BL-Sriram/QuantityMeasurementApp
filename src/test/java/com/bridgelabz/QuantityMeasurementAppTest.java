package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {
    @Test
    void testEquality_YardToYard_SameValue(){
        assertEquals(new QuantityLength(1.0, LengthUnit.YARD),
                 new QuantityLength(1.0, LengthUnit.YARD));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(
                new QuantityLength(1, LengthUnit.YARD),
                new QuantityLength(3, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertEquals(
                new QuantityLength(1, LengthUnit.YARD),
                new QuantityLength(36, LengthUnit.INCH)
        );
    }

    @Test
    void testEquality_YardToFeet_DifferentValue() {
        assertNotEquals(
                new QuantityLength(1, LengthUnit.YARD),
                new QuantityLength(2, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_CmToCm_SameValue() {
        assertEquals(
                new QuantityLength(2, LengthUnit.CENTIMETER),
                new QuantityLength(2, LengthUnit.CENTIMETER)
        );
    }

    @Test
    void testEquality_CmToInch_EquivalentValue() {
        assertEquals(
                new QuantityLength(1, LengthUnit.CENTIMETER),
                new QuantityLength(0.393701, LengthUnit.INCH)
        );
    }

    @Test
    void testEquality_CmToFeet_NonEquivalent() {
        assertNotEquals(
                new QuantityLength(1, LengthUnit.CENTIMETER),
                new QuantityLength(1, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_TransitiveProperty() {

        QuantityLength yard =
                new QuantityLength(1, LengthUnit.YARD);

        QuantityLength feet =
                new QuantityLength(3, LengthUnit.FEET);

        QuantityLength inch =
                new QuantityLength(36, LengthUnit.INCH);

        assertEquals(yard, feet);
        assertEquals(feet, inch);
        assertEquals(yard, inch);
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q =
                new QuantityLength(1, LengthUnit.YARD);

        assertEquals(q, q);
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q =
                new QuantityLength(1, LengthUnit.YARD);

        assertNotEquals(q, null);
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(1, null)
        );
    }
}