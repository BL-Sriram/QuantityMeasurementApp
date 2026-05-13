package com.quantity_measurement.quantity_service.model;

import com.quantity_measurement.quantity_service.interfaces.IMeasurable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuantityModel<U extends IMeasurable> {
    public double value;
    public U unit;
}