package com.quantity_measurement.quantity_service.enums;

import com.quantity_measurement.quantity_service.interfaces.*;
import com.quantity_measurement.quantity_service.interfaces.ArithmeticCapable;
import com.quantity_measurement.quantity_service.interfaces.IMeasurable;

public enum TemperatureUnit implements IMeasurable, ArithmeticCapable {

    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    public double convertToBaseUnit(double value) {
        return switch (this) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5 / 9;
            case KELVIN -> value - 273.15;
        };
    }

    public double convertFromBaseUnit(double value) {
        return switch (this) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value * 9 / 5) + 32;
            case KELVIN -> value + 273.15;
        };
    }

    public String getUnitName() {
        return name();
    }

    public boolean supportsArithmetic() {
        return false;
    }
}