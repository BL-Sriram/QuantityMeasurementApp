package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityDTO;

public interface IQuantityMeasurementService {
    double add(QuantityDTO a, QuantityDTO b);
    boolean compare(QuantityDTO a, QuantityDTO b);
}