package com.example.quantity_measurement_app.enums;

import com.example.quantity_measurement_app.interfaces.*;

public enum VolumeUnit implements IMeasurable, ArithmeticCapable {

    LITER(1.0),
    MILLILITER(0.001),
    GALLON(3.785);

    private final double factor;

    VolumeUnit(double factor) {
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