package com.bridgelabz;

public interface IMeasurable{
    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    default void validateOperationSupport(String operation){
        // By default all operations are supported
    }

    String getUnitName();
}