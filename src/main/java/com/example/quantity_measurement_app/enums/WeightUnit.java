package com.example.quantity_measurement_app.enums;

import com.example.quantity_measurement_app.interfaces.*;

public enum WeightUnit implements IMeasurable, ArithmeticCapable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double value) {
        return value / factor;
    }

    public String getUnitName() {
        return name();
    }

    public boolean supportsArithmetic() {
        return true;
    }
}