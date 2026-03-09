package com.bridgelabz;


/** Generic immutable quantity class.
 * Works with any unit implementing IMeasurable. */

final class Quantity<U extends IMeasurable> {
    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // Conversion
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = this.toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    // Addition
    public Quantity<U> add(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        double sumBase =
                this.toBaseUnit() + other.toBaseUnit();

        double result =
                this.unit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase =
                this.toBaseUnit() + other.toBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    // Equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;
        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}

public class QuantityMeasurementApp {
    // Generic Conversion
    public static <U extends IMeasurable>
    void demonstrateConversion(double value, U from, U to) {

        Quantity<U> quantity = new Quantity<>(value, from);
        Quantity<U> converted = quantity.convertTo(to);

        System.out.println(
                "convert(" + value + ", " + from.getUnitName() + ", "
                        + to.getUnitName() + ") ΓåÆ " + converted.getValue()
        );
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(Quantity<U> quantity, U target) {

        Quantity<U> converted = quantity.convertTo(target);

        System.out.println(quantity + " ΓåÆ " + converted);
    }

    // Generic Addition
    public static <U extends IMeasurable>
    void demonstrateAddition(Quantity<U> q1,
                             Quantity<U> q2,
                             U targetUnit) {

        Quantity<U> result = q1.add(q2, targetUnit);

        System.out.println(
                q1 + " + " + q2 + " ΓåÆ " + result
        );
    }

    // Generic Equality
    public static <U extends IMeasurable>
    void demonstrateEquality(Quantity<U> q1,
                             Quantity<U> q2) {

        System.out.println(
                q1 + " equals " + q2 + " ΓåÆ " + q1.equals(q2)
        );
    }

    public static void main(String[] args) {
//        Quantity<VolumeUnit> v1 = new Quantity<>(1000, VolumeUnit.LITRE);
//        Quantity<VolumeUnit> v2 = new Quantity<>(1, VolumeUnit.MILLILITRE);
//
//        boolean result = v1.equals(v2);
//        System.out.println("result " + result);

        Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);

        boolean result = (v1.equals(v2));
        System.out.println("result " + result);
    }
}