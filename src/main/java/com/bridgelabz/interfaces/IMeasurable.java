package com.bridgelabz.interfaces;

public interface IMeasurable {
    String getUnitName();
    String getMeasurementType();
    double toBaseUnit(double value);
}