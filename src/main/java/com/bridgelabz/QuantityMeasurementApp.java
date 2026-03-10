package com.bridgelabz;


/** Generic immutable quantity class.
 * Works with any unit implementing IMeasurable. */

final class Quantity<U extends IMeasurable> {
    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;
    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit required");

        if (targetUnit != null &&
                !unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Invalid target unit type");
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return operation.compute(thisBase, otherBase);
    }

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

    private double roundToDecimal(double value){
        return Math.round(value * 100.0) / 100.0;
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
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        unit.validateOperationSupport("ADD");

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double finalValue =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToDecimal(finalValue), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double finalValue =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToDecimal(finalValue), targetUnit);
    }

    public double divide(Quantity<U> other) {
        unit.validateOperationSupport("DIVIDE");

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
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
    void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        Quantity<U> result = q1.add(q2, targetUnit);

        System.out.println(
                q1 + " + " + q2 + " ΓåÆ " + result
        );
    }

    // Generic Equality
    public static <U extends IMeasurable>
    void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {

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