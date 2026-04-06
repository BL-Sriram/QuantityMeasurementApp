package com.bridgelabz.controller;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.service.*;

public class QuantityMeasurementController {
    private IQuantityMeasurementService service = new QuantityMeasurementServiceImpl();

    public double add(QuantityDTO a, QuantityDTO b) {
        return service.add(a, b);
    }

    public boolean compare(QuantityDTO a, QuantityDTO b) {
        return service.compare(a, b);
    }
}