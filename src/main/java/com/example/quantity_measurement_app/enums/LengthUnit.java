package com.example.quantity_measurement_app.enums;

import com.example.quantity_measurement_app.interfaces.*;

public enum LengthUnit implements IMeasurable, ArithmeticCapable {

    METER(1.0),
    CENTIMETER(0.01),
    KILOMETER(1000),
    INCH(0.0254);

    private final double factor;

    LengthUnit(double factor) {
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