package com.example.quantity_measurement_app.interfaces;

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double value);
    String getUnitName();
}