package com.example.quantity_measurement_app.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
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
    private boolean isError;
    private String errorMessage;
    private LocalDateTime createdAt;

    @PrePersist
    public void setTime() {
        this.createdAt = LocalDateTime.now();
    }
}