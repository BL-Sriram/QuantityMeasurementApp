package com.quantity_measurement.quantity_service.enums;

import com.quantity_measurement.quantity_service.interfaces.*;
import com.quantity_measurement.quantity_service.interfaces.ArithmeticCapable;
import com.quantity_measurement.quantity_service.interfaces.IMeasurable;

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