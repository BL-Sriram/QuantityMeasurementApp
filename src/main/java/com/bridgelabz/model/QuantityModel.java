package com.bridgelabz.model;

import com.bridgelabz.interfaces.IMeasurable;

public class QuantityModel<U extends IMeasurable> {
    public double value;
    public U unit;
}