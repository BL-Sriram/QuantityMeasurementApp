package com.quantity_measurement.quantity_service.enums;

import com.quantity_measurement.quantity_service.interfaces.*;
import com.quantity_measurement.quantity_service.interfaces.ArithmeticCapable;
import com.quantity_measurement.quantity_service.interfaces.IMeasurable;

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