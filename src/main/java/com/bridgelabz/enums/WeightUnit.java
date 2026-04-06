package com.bridgelabz.enums;

import com.bridgelabz.interfaces.IMeasurable;
import com.bridgelabz.interfaces.SupportsArithmetic;

public enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factorToKilogram;
    WeightUnit(double factorToKilogram){
        this.factorToKilogram = factorToKilogram;
    }

    SupportsArithmetic supportsArithmetic = () -> true;

    @Override
    // Converts value in this unit to base unit(Kilogram)
    public double convertToBaseUnit(double value){
        return value * factorToKilogram;
    }

    @Override
    // Converts base unit to this unit
    public double convertFromBaseUnit(double value){
        return value / factorToKilogram;
    }


    public double getConversionFactor() {
        return factorToKilogram;
    }

    @Override
    public String getUnitName(){
        return name();
    }
}