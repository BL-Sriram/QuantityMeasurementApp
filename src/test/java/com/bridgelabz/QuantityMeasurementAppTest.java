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

    // SAME UNIT ADDITION

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

    // CROSS UNIT ADDITION

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

    // COMMUTATIVITY

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

    // ZERO IDENTITY

    @Test
    void testAddition_WithZero() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2);

        assertEquals(5.0, result.getValue(), EPS);
    }

    // NEGATIVE VALUES

    @Test
    void testAddition_NegativeValues() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(3.0, result.getValue(), EPS);
    }

    // NULL VALIDATION

    @Test
    void testAddition_NullSecondOperand() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(null));
    }

    // LARGE VALUES

    @Test
    void testAddition_LargeValues() {
        QuantityLength q1 = new QuantityLength(1e6, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1e6, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(2e6, result.getValue(), EPS);
    }

    // SMALL VALUES

    @Test
    void testAddition_SmallValues() {
        QuantityLength q1 = new QuantityLength(0.001, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.002, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(0.003, result.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = a.add(b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Explicit Target = INCHES
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = a.add(b, LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPS);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    // Explicit Target = YARDS
    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = a.add(b, LengthUnit.YARD);

        assertEquals(2.0 / 3.0, result.getValue(), EPS);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    // Explicit Target = CENTIMETERS
    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength b = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result = a.add(b, LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 1e-2);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    // Target Same As First Operand
    @Test
    void testAddition_TargetSameAsFirstOperand() {
        QuantityLength a = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = a.add(b, LengthUnit.YARD);

        assertEquals(3.0, result.getValue(), EPS);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    // Target Same As Second Operand
    @Test
    void testAddition_TargetSameAsSecondOperand() {
        QuantityLength a = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = a.add(b, LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Commutativity With Explicit Target
    @Test
    void testAddition_Commutativity_WithExplicitTarget() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result1 = a.add(b, LengthUnit.YARD);
        QuantityLength result2 = b.add(a, LengthUnit.YARD);

        assertEquals(result1.getValue(), result2.getValue(), EPS);
        assertEquals(result1.getUnit(), result2.getUnit());
    }

    // Zero Value With Explicit Target
    @Test
    void testAddition_WithZero_ExplicitTarget() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result = a.add(b, LengthUnit.YARD);

        assertEquals(5.0 / 3.0, result.getValue(), EPS);
    }

    // Negative Values
    @Test
    void testAddition_NegativeValues_ExplicitTarget() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = a.add(b, LengthUnit.INCH);

        assertEquals(36.0, result.getValue(), EPS);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    // Null Target Unit
    @Test
    void testAddition_NullTargetUnit() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class,
                () -> a.add(b, null));
    }

    // Large To Small Scale
    @Test
    void testAddition_LargeToSmallScale() {
        QuantityLength a = new QuantityLength(1000.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(500.0, LengthUnit.FEET);

        QuantityLength result = a.add(b, LengthUnit.INCH);

        assertEquals(18000.0, result.getValue(), EPS);
    }

    // Small To Large Scale
    @Test
    void testAddition_SmallToLargeScale() {
        QuantityLength a = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = a.add(b, LengthUnit.YARD);

        assertEquals(2.0 / 3.0, result.getValue(), EPS);
    }

    // Precision Tolerance
    @Test
    void testAddition_PrecisionTolerance() {
        QuantityLength a = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength b = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result = a.add(b, LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    @Test
    void testUnitStandaloneConversion() {

        assertEquals(1.0,
                LengthUnit.INCH.toFeet(12.0),
                EPS);

        assertEquals(12.0,
                LengthUnit.INCH.fromFeet(1.0),
                EPS);
    }

    @Test
    void testConvertTo() {

        QuantityLength q =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result =
                q.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_DefaultUnit() {

        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength b =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = a.add(b);

        assertEquals(2.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTarget() {

        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength b =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                a.add(b, LengthUnit.YARD);

        assertEquals(2.0 / 3.0, result.getValue(), EPS);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testEquality() {

        QuantityLength a =
                new QuantityLength(36.0, LengthUnit.INCH);

        QuantityLength b =
                new QuantityLength(1.0, LengthUnit.YARD);

        assertEquals(a, b);
    }

    @Test
    void testNullValidation() {

        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> a.add(null));

        assertThrows(IllegalArgumentException.class,
                () -> a.add(a, null));
    }

    // Equality
    @Test
    void testEquality_SameUnit() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertEquals(a, b);
    }

    @Test
    void testEquality_CrossUnit() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(kg, g);
    }

    @Test
    void testEquality_PoundEquivalent() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight lb = new QuantityWeight(2.20462262, WeightUnit.POUND);

        assertEquals(kg, lb);
    }

    // Conversion
    @Test
    void testConversion_KgToGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_GramToPound() {
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = g.convertTo(WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), 1e-3);
    }

    // Addition
    @Test
    void testAddition_SameUnit() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = a.add(b);

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(500.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(g);

        assertEquals(1.5, result.getValue(), EPS);
    }

    @Test
    void testAddition_WithTargetUnit() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(500.0, WeightUnit.GRAM);

        QuantityWeight result =
                kg.add(g, WeightUnit.GRAM);

        assertEquals(1500.0, result.getValue(), EPS);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // Category Separation
    @Test
    void testLengthAndWeightNotComparable() {

        QuantityWeight weight =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityLength length =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(weight, length);
    }
}