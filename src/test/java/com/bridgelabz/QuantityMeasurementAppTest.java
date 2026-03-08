package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthConversionTest {
    private static final double EPS = 1e-6;

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
                new Quantity<>(12.0, LengthUnit.INCH);

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

    // Scalability Test (New Enum Integration)
    enum VolumeUnit implements IMeasurable {

        LITER(1.0),
        MILLILITER(0.001);

        private final double factor;

        VolumeUnit(double factor) {
            this.factor = factor;
        }

        @Override
        public double getConversionFactor() {
            return factor;
        }

        @Override
        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        @Override
        public String getUnitName() {
            return name();
        }
    }

    @Test
    void testScalability_NewUnitEnumIntegration() {

        Quantity<VolumeUnit> liter =
                new Quantity<>(1.0, VolumeUnit.LITER);

        Quantity<VolumeUnit> ml =
                liter.convertTo(VolumeUnit.MILLILITER);

        assertEquals(1000.0, ml.getValue(), EPS);
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
}