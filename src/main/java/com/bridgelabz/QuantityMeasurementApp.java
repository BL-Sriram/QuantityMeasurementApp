package com.bridgelabz;

import com.bridgelabz.controller.QuantityMeasurementController;
import com.bridgelabz.dto.QuantityDTO;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityMeasurementController controller = new QuantityMeasurementController();

        QuantityDTO q1 = new QuantityDTO();
        q1.value = 1;
        q1.unit = "FEET";

        QuantityDTO q2 = new QuantityDTO();
        q2.value = 12;
        q2.unit = "INCH";

        System.out.println("Equal: " + controller.compare(q1, q2));
        System.out.println("Sum: " + controller.add(q1, q2));
    }
}