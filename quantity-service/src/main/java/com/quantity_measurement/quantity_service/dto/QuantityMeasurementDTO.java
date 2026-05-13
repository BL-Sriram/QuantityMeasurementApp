package com.quantity_measurement.quantity_service.dto;

import com.quantity_measurement.quantity_service.model.QuantityMeasurementEntity;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityMeasurementDTO {

    private String operation;
    private String measurementType;
    private Double inputValue;
    private Double resultValue;
    private boolean error;

    private String errorMessage;
    private LocalDateTime createdAt;

    public static QuantityMeasurementDTO fromEntity(QuantityMeasurementEntity e) {
        return new QuantityMeasurementDTO(
                e.getOperation(),
                e.getMeasurementType(),
                e.getInputValue(),
                e.getResultValue(),
                e.isError(),
                e.getErrorMessage(),
                e.getCreatedAt()
        );
    }
}