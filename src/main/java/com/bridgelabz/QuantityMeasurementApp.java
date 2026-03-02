package com.bridgelabz;


// Enum represents all supported length units
// Each unit knows how to convert itself to the base unit (FEET)
enum LengthUnit{
    // FEET is base unit ΓåÆ conversion factor = 1.0
    FEET(1.0),
    INCH(1.0 / 12.0);

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
        QuantityLength q1 = new QuantityLength(1.0,LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0,LengthUnit.INCH);

        System.out.println(q1.equals(q2));
    }
}