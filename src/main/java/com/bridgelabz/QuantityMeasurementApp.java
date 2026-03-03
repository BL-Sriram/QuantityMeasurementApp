package com.bridgelabz;


// Enum represents all supported length units
// Each unit knows how to convert itself to the base unit (FEET)
enum LengthUnit{
    // FEET is base unit ΓåÆ conversion factor = 1.0
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    // 1 YARD = 3 feet
    CENTIMETER(0.393701 / 12.0);
    // 1 cm = 0.393701 inch
    // 1 inch = 1/12 feet
    // so cm to feet = 0.393701 / 12

    // Each enum constant stores its conversion factor to FEET
    private final double conversionFactorToFeet;

    // Constructor runs once for each enum constant
    LengthUnit(double conversionFactorToFeet){
        this.conversionFactorToFeet = conversionFactorToFeet;
    }

    // Converts given value into FEET
    public double toFeet(double value){
        return value * conversionFactorToFeet;
    }
}

// new one better design
final class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    // Converts this object to base unit (FEET)
    private double toBaseUnit() {
        // calls enum method to convert
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {
        // same reference check
        if (this == obj) return true;

        // null and type check
        if (obj == null || getClass() != this.getClass()) return false;

        // safe casting
        QuantityLength other = (QuantityLength) obj;

        // compare using Double.compare()
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }
}


public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.YARD);

        QuantityLength q2 =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(q1.equals(q2)); // true

        QuantityLength q3 =
                new QuantityLength(36.0, LengthUnit.INCH);

        System.out.println(q1.equals(q3)); // true

        QuantityLength q4 =
                new QuantityLength(1.0, LengthUnit.CENTIMETER);

        QuantityLength q5 =
                new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println(q4.equals(q5)); // true
    }
}