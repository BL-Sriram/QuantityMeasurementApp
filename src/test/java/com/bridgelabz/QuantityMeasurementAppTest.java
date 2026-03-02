package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {
    @Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(5.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(24.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_Feet_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_Inch_DifferentValue() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(10.0, LengthUnit.INCH);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null)
        );
    }
}