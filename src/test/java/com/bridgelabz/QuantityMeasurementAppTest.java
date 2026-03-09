package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthConversionTest {
    private static final double EPS = 1e-6;
    private static final double EPSILON = 0.0001;

    // IMeasurable Interface Validation
    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        IMeasurable unit = LengthUnit.FEET;

        assertEquals(1.0,
                unit.convertToBaseUnit(1.0),
                EPS);

        assertEquals(12.0,
                LengthUnit.INCH.convertFromBaseUnit(1.0),
                EPS);
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        IMeasurable unit = WeightUnit.KILOGRAM;

        assertEquals(1.0,
                unit.convertToBaseUnit(1.0),
                EPS);

        assertEquals(1000.0,
                WeightUnit.GRAM.convertFromBaseUnit(1.0),
                EPS);
    }

    // Generic Equality
    @Test
    void testGenericQuantity_LengthEquality() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCH);

        assertEquals(a, b);
    }

    @Test
    void testGenericQuantity_WeightEquality() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(a, b);
    }

    // Conversion
    @Test
    void testGenericQuantity_LengthConversion() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                feet.convertTo(LengthUnit.INCH);

        assertEquals(12.0, inches.getValue(), EPS);
    }

    @Test
    void testGenericQuantity_WeightConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, gram.getValue(), EPS);
    }

    // Addition
    @Test
    void testGenericQuantity_LengthAddition() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> result =
                a.add(b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testGenericQuantity_WeightAddition() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                a.add(b, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), EPS);
    }

    // Cross Category Prevention
    @Test
    void testCrossCategoryPrevention_EqualsReturnsFalse() {
        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    // NOTE:
    // Compiler type safety cannot be tested via runtime JUnit.
    // This is compile-time enforced:
    //
    // length.add(weight);  // DOES NOT COMPILE

    // Constructor Validation
    @Test
    void testConstructorValidation_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testConstructorValidation_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    // Equality Contract
    @Test
    void testEqualsContract() {
        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> c =
                new Quantity<>(12.0 , LengthUnit.INCH);

        // Reflexive
        assertEquals(a, a);

        // Symmetric
        assertEquals(a, b);
        assertEquals(b, a);

        // Transitive
        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    // HashCode Consistency
    @Test
    void testHashCodeConsistency() {
        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCH);

        assertEquals(a.hashCode(), b.hashCode());
    }

    // Immutability
    @Test
    void testImmutability() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                a.convertTo(LengthUnit.INCH);

        assertNotSame(a, b);
    }

    // Wildcard Support
    @Test
    void testTypeWildcard_FlexibleSignatures() {
        Quantity<?> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<?> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotNull(length);
        assertNotNull(weight);
    }

    // Volume Test
    @Test
    void testLitreToLitreEquality() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testLitreToMillilitreEquality() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testLitreToGallonEquality() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(0.264172, VolumeUnit.GALLON);

        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v1)); // symmetric
    }

    @Test
    void testMillilitreToLitreEquality() {
        Quantity<VolumeUnit> v1 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(0.5, VolumeUnit.LITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testGallonToLitreEquality() {
        Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testLitreToMillilitreConversion() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testGallonToLitreConversion() {
        Quantity<VolumeUnit> v = new Quantity<>(2.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

        assertEquals(7.57082, result.getValue(), EPSILON);
    }

    @Test
    void testMillilitreToGallonConversion() {
        Quantity<VolumeUnit> v = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.GALLON);

        assertEquals(0.132086, result.getValue(), EPSILON);
    }

    @Test
    void testZeroConversion() {
        Quantity<VolumeUnit> v = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testSameUnitConversion() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testSameUnitAddition() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void testCrossUnitAddition() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testExplicitMillilitreTarget() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                v1.add(v2, VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testExplicitGallonTarget() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                v1.add(v2, VolumeUnit.GALLON);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testVolumeVsLength() {
        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(volume.equals(length));
    }

    @Test
    void testVolumeVsWeight() {
        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(volume.equals(weight));
    }

    @Test
    void testNullComparison() {
        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertFalse(volume.equals(null));
    }

    @Test
    void testNegativeValues() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(-1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    // Subtraction And Division
    @Test
    void testSameUnitSubtraction() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v1.subtract(v2);

        assertEquals(3.0, result.getValue(), EPS);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void testCrossUnitSubtractionImplicit() {
        Quantity<LengthUnit> l1 =
                new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 =
                new Quantity<>(6.0, LengthUnit.INCH);

        Quantity<LengthUnit> result = l1.subtract(l2);

        assertEquals(9.5, result.getValue(), EPS);
    }

    @Test
    void testCrossUnitSubtractionExplicit() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v2 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                v1.subtract(v2, VolumeUnit.LITRE);

        assertEquals(2.79, result.getValue(), EPS);
    }

    @Test
    void testNegativeResult() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v1.subtract(v2);

        assertEquals(-3.0, result.getValue(), EPS);
    }

    @Test
    void testZeroResult() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.subtract(v2);

        assertEquals(0.0, result.getValue(), EPS);
    }
    @Test
    void testSimpleDivision() {
        Quantity<WeightUnit> w1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        double result = w1.divide(w2);

        assertEquals(2.0, result, EPS);
    }

    @Test
    void testCrossUnitDivision() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        double result = v1.divide(v2);

        assertEquals(1.0, result, EPS);
    }

    @Test
    void testResultLessThanOne() {
        Quantity<LengthUnit> l1 =
                new Quantity<>(6.0, LengthUnit.INCH);
        Quantity<LengthUnit> l2 =
                new Quantity<>(1.0, LengthUnit.FEET);

        double result = l1.divide(l2);

        assertEquals(0.5, result, EPS);
    }

    @Test
    void testDivisionByZero() {
        Quantity<WeightUnit> w1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 =
                new Quantity<>(0.0, WeightUnit.KILOGRAM);

        assertThrows(ArithmeticException.class,
                () -> w1.divide(w2));
    }

    @Test
    void testCrossCategorySubtraction() {
        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> volume.subtract((Quantity) length));
    }

    @Test
    void testNullSubtraction() {
        Quantity<VolumeUnit> v =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertThrows(IllegalArgumentException.class,
                () -> v.subtract(null));
    }
}