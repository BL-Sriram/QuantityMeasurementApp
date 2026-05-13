package com.quantity_measurement.quantity_service.controller;

import com.quantity_measurement.quantity_service.dto.*;
import com.quantity_measurement.quantity_service.service.IQuantityMeasurementService;
import com.quantity_measurement.quantity_service.dto.QuantityDTO;
import com.quantity_measurement.quantity_service.dto.QuantityMeasurementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/convert")
    public QuantityMeasurementDTO convert(@RequestBody QuantityDTO dto) {
        return service.convert(dto);
    }
}