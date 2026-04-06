package com.bridgelabz.enums;

import com.bridgelabz.interfaces.IMeasurable;
import com.bridgelabz.interfaces.SupportsArithmetic;

public enum LengthUnit implements IMeasurable {
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
    @Override
    public double convertToBaseUnit(double value){
        return value * conversionFactorToFeet;
    }

    SupportsArithmetic supportsArithmetic = () -> true;

    @Override
    public double convertFromBaseUnit(double value){
        return (value / conversionFactorToFeet);
    }

    public double getConversionFactor(){
        return conversionFactorToFeet;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}