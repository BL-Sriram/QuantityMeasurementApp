package com.example.quantity_measurement_app.model;

import com.example.quantity_measurement_app.interfaces.IMeasurable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuantityModel<U extends IMeasurable> {
    public double value;
    public U unit;
}