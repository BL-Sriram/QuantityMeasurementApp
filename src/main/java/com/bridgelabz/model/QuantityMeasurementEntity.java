package com.bridgelabz.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QuantityMeasurementEntity implements Serializable {
    private double value;
    private String unit;
    private String operation;

    private boolean isError;
    private String errorMessage;

    public QuantityMeasurementEntity(double value, String unit, String operation) {
        this.value = value;
        this.unit = unit;
        this.operation = operation;
    }

    public QuantityMeasurementEntity(String errorMessage) {
        this.isError = true;
        this.errorMessage = errorMessage;
    }

    public QuantityMeasurementEntity() {
    }
}