package com.bridgelabz.model;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {
    public double resultValue;
    public String operation;
    public boolean isError;
    public String errorMessage;

    public QuantityMeasurementEntity(double resultValue, String operation) {
        this.resultValue = resultValue;
        this.operation = operation;
    }

    public QuantityMeasurementEntity(String errorMessage) {
        this.isError = true;
        this.errorMessage = errorMessage;
    }
}