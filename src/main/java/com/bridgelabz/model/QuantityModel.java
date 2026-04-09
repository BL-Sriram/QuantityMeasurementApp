package com.bridgelabz.model;

import com.bridgelabz.interfaces.IMeasurable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuantityModel<U extends IMeasurable> {
    public double value;
    public U unit;
}