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

    public double fromFeet(double value){
        return (value / conversionFactorToFeet);
    }

    public double getFactor(){
        return conversionFactorToFeet;
    }
}

// enum for Weight
enum WeightUnit{
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factorToKilogram;
    WeightUnit(double factorToKilogram){
        this.factorToKilogram = factorToKilogram;
    }

    // Converts value in this unit to base unit(Kilogram)
    public double convertToBaseUnit(double value){
        return value * factorToKilogram;
    }

    // Converts base unit to this unit
    public double convertFromBaseUnit(double value){
        return value / factorToKilogram;
    }
}


// Length Unit Class
final class QuantityLength{
    // if we are using A final field it must be initialized in every constructor.
    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-9;

    QuantityLength(double value, LengthUnit unit) {
        if(!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");

        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    // Converts this object to base unit (FEET)
    private double toBaseUnit() {
        // calls enum method to convert
        return unit.toFeet(value);
    }

    // Equality
    @Override
    public boolean equals(Object obj) {
        // same reference check
        if (this == obj) return true;

        // null and type check
        if (obj == null || getClass() != this.getClass()) return false;

        // safe casting
        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    /*
     * Converts this quantity to another unit.
     * Returns a NEW immutable instance.
     */
    public QuantityLength convertTo(LengthUnit targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException("Unit cannot be null");

        double baseValue = unit.toFeet(value);
        double converted = targetUnit.fromFeet(baseValue);

        return new QuantityLength(converted, targetUnit);
    }

    // Static Utility Conversion API
    public static double convert(double value, LengthUnit source, LengthUnit target){
        if(!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");

        if(source == null || target == null) throw new IllegalArgumentException("Unit cannot be null");

        if(source == target) return value;

        double baseValue = source.toFeet(value);

        return target.fromFeet(baseValue);
    }


    // Method for adding value
    public QuantityLength add(QuantityLength other){
        if(other == null){
            throw new IllegalArgumentException("Second operand cannot be null");
        }
        if(!Double.isFinite(this.value) || !Double.isFinite(other.value)){
            throw new IllegalArgumentException("Values must be finite");
        }

        double thisInFeet = this.unit.toFeet(this.value);
        double otherInFeet = other.unit.toFeet(other.value);

        double sumInFeet = thisInFeet + otherInFeet;

        double resultValue = this.unit.fromFeet(sumInFeet);

        return new QuantityLength(resultValue, this.unit);
    }

    // Method for adding value with targetUnit
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit){
        if(other == null){
            throw new IllegalArgumentException("Second operand cannot be null");
        }
        if(!Double.isFinite(this.value) || !Double.isFinite(other.value)){
            throw new IllegalArgumentException("Values must be finite");
        }
        if(targetUnit == null){
            throw new IllegalArgumentException("TargetUnit cannot be null");
        }

        double thisInFeet = this.unit.toFeet(this.value);
        double otherInFeet = other.unit.toFeet(other.value);

        double sumInFeet = thisInFeet + otherInFeet;

        double resultValue = targetUnit.fromFeet(sumInFeet);

        return new QuantityLength(resultValue, targetUnit);
    }

    // Getter Methods
    public double getValue(){
        return value;
    }

    public LengthUnit getUnit(){
        return unit;
    }

    @Override
    public String toString(){
        return value + " " + unit.name();
    }
}

// Weight Unit Class
final class QuantityWeight{
    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON = 1e-6;

    private double toBaseUnit(){
        return unit.convertToBaseUnit(value);
    }

    // Constructor
    public QuantityWeight(double value, WeightUnit unit){
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue(){
        return value;
    }

    public WeightUnit getUnit(){
        return unit;
    }

    // Conversion
    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = this.toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new QuantityWeight(converted, targetUnit);
    }

    // Addition
    public QuantityWeight add(QuantityWeight other) {
        if (other == null)
            throw new IllegalArgumentException("Other weight cannot be null");

        double sumBase =
                this.toBaseUnit() + other.toBaseUnit();

        double result =
                this.unit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(result, this.unit);
    }

    // Addition with target unit
    public QuantityWeight add(QuantityWeight other,
                              WeightUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other weight cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase =
                this.toBaseUnit() + other.toBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(result, targetUnit);
    }

    // Equality Comparison
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        QuantityWeight other = (QuantityWeight) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public String toString() {
        return "QuantityWeight(" + value + ", " + unit + ")";
    }
}

public class QuantityMeasurementApp {
    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to){
        double result = QuantityLength.convert(value, from, to);

        System.out.println("convert(" + value + ", " + from + ", " + to + ") ΓåÆ " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit target){
        QuantityLength converted = quantity.convertTo(target);

        System.out.println(quantity + " ΓåÆ " + converted);
    }

    public static void main(String[] args) {
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = yard.add(inch, LengthUnit.CENTIMETER);

        demonstrateLengthConversion(yard, LengthUnit.INCH);

        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        boolean result1 = q1.equals(q2);
        System.out.println("test" + result1);


        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight lb = new QuantityWeight(2.20462262, WeightUnit.POUND);

        boolean r2 = kg.equals(lb);
        System.out.println("test2 " + r2);
        System.out.println();
    }
}