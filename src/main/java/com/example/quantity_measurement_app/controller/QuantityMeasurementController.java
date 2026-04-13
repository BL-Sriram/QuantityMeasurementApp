package com.example.quantity_measurement_app.controller;

import com.example.quantity_measurement_app.dto.*;
import com.example.quantity_measurement_app.service.IQuantityMeasurementService;
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