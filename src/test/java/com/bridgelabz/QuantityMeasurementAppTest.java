package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthConversionTest {
    private static final double EPS = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH),
                EPS);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityLength.convert(1.0, LengthUnit.YARD, LengthUnit.INCH),
                EPS);
    }

    @Test
    void testConversion_CentimeterToInch() {
        assertEquals(1.0,
                QuantityLength.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH),
                1e-3);
    }

    @Test
    void testConversion_RoundTrip() {

        double original = 5.0;

        double converted =
                QuantityLength.convert(original,
                        LengthUnit.FEET,
                        LengthUnit.INCH);

        double back =
                QuantityLength.convert(converted,
                        LengthUnit.INCH,
                        LengthUnit.FEET);

        assertEquals(original, back, EPS);
    }

    @Test
    void testConversion_InvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testConversion_NaN() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCH));
    }

    // ---------------- SAME UNIT ADDITION ----------------

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(3.0,
                QuantityLength.convert(result.getValue(),
                        result.getUnit(),
                        LengthUnit.FEET),
                EPS);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        QuantityLength q1 = new QuantityLength(6.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(6.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2);

        assertEquals(12.0,
                result.getValue(),
                EPS);
    }

    // ---------------- CROSS UNIT ADDITION ----------------

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2);

        assertEquals(2.0,
                result.getValue(),
                EPS);
        assertEquals(LengthUnit.FEET,
                result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(24.0,
                result.getValue(),
                EPS);
        assertEquals(LengthUnit.INCH,
                result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(2.0,
                result.getValue(),
                EPS);
        assertEquals(LengthUnit.YARD,
                result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        QuantityLength q1 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2);

        assertEquals(5.08,
                result.getValue(),
                1e-2); // slightly larger tolerance
        assertEquals(LengthUnit.CENTIMETER,
                result.getUnit());
    }

    // ---------------- COMMUTATIVITY ----------------

    @Test
    void testAddition_Commutativity() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result1 = a.add(b);
        QuantityLength result2 = b.add(a);

        double result1InFeet =
                QuantityLength.convert(result1.getValue(),
                        result1.getUnit(),
                        LengthUnit.FEET);

        double result2InFeet =
                QuantityLength.convert(result2.getValue(),
                        result2.getUnit(),
                        LengthUnit.FEET);

        assertEquals(result1InFeet, result2InFeet, EPS);
    }

    // ---------------- ZERO IDENTITY ----------------

    @Test
    void testAddition_WithZero() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2);

        assertEquals(5.0, result.getValue(), EPS);
    }

    // ---------------- NEGATIVE VALUES ----------------

    @Test
    void testAddition_NegativeValues() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(3.0, result.getValue(), EPS);
    }

    // ---------------- NULL VALIDATION ----------------

    @Test
    void testAddition_NullSecondOperand() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(null));
    }

    // ---------------- LARGE VALUES ----------------

    @Test
    void testAddition_LargeValues() {
        QuantityLength q1 = new QuantityLength(1e6, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1e6, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(2e6, result.getValue(), EPS);
    }

    // ---------------- SMALL VALUES ----------------

    @Test
    void testAddition_SmallValues() {
        QuantityLength q1 = new QuantityLength(0.001, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.002, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(0.003, result.getValue(), EPS);
    }
}