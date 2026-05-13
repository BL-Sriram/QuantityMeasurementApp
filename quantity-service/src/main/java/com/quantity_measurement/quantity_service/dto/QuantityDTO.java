package com.quantity_measurement.quantity_service.dto;

import lombok.Data;

@Data
public class QuantityDTO {
    private Double value;
    private String fromUnit;
    private String toUnit;
    private String measurementType;
}