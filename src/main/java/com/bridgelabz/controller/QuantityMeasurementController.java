package com.bridgelabz.controller;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.repository.IQuantityMeasurementRepository;
import com.bridgelabz.service.IQuantityMeasurementService;
import com.bridgelabz.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementRepository repo) {
        this.service = new QuantityMeasurementServiceImpl(repo);
    }

    public double add(QuantityDTO a, QuantityDTO b) {
        return service.add(a, b);
    }

    public boolean compare(QuantityDTO a, QuantityDTO b) {
        return service.compare(a, b);
    }
}