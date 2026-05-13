package com.quantity_measurement.quantity_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;

    private String measurementType;

    private Double inputValue;

    private Double resultValue;

    private boolean error;

    private String errorMessage;

    private LocalDateTime createdAt;
}