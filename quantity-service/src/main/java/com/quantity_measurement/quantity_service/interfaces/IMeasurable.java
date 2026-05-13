package com.quantity_measurement.quantity_service.interfaces;

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double value);
    String getUnitName();
}