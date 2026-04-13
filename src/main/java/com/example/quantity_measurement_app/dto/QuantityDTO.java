package com.example.quantity_measurement_app.dto;

import lombok.Data;

@Data
public class QuantityDTO {
    private Double value;
    private String fromUnit;
    private String toUnit;
    private String measurementType;
}