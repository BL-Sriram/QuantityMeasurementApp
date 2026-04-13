package com.example.quantity_measurement_app.service;

import com.example.quantity_measurement_app.dto.*;

public interface IQuantityMeasurementService {
    QuantityMeasurementDTO convert(QuantityDTO dto);
}