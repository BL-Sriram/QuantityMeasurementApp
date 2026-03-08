package com.bridgelabz;

/**
 * Common interface for all measurable unit types.
 * Defines contract for base unit conversion.
 */
public interface IMeasurable{
    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
}