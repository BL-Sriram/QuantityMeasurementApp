package com.quantity_measurement.quantity_service.service;

import com.quantity_measurement.quantity_service.dto.*;
import com.quantity_measurement.quantity_service.dto.QuantityDTO;
import com.quantity_measurement.quantity_service.dto.QuantityMeasurementDTO;

public interface IQuantityMeasurementService {
    QuantityMeasurementDTO convert(QuantityDTO dto);
}