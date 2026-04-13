package com.example.quantity_measurement_app.controller;

import com.example.quantity_measurement_app.dto.QuantityDTO;
import com.example.quantity_measurement_app.dto.QuantityMeasurementDTO;
import com.example.quantity_measurement_app.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
@AutoConfigureMockMvc(addFilters = false)
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IQuantityMeasurementService service;

    @Test
    void testWeightConversion() throws Exception {

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO("CONVERT", "WEIGHT", 1.0, 1000.0, false, null, null);

        when(service.convert(any())).thenReturn(response);

        QuantityDTO dto = new QuantityDTO();
        dto.setValue(1.0);
        dto.setFromUnit("KILOGRAM");
        dto.setToUnit("GRAM");
        dto.setMeasurementType("WEIGHT");

        mockMvc.perform(post("/api/v1/quantities/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value(1000.0))
                .andExpect(jsonPath("$.error").value(false)); // Γ£à FIXED
    }

    @Test
    void testLengthConversion() throws Exception {

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO("CONVERT", "LENGTH", 1.0, 100.0, false, null, null);

        when(service.convert(any())).thenReturn(response);

        QuantityDTO dto = new QuantityDTO();
        dto.setValue(1.0);
        dto.setFromUnit("METER");
        dto.setToUnit("CENTIMETER");
        dto.setMeasurementType("LENGTH");

        mockMvc.perform(post("/api/v1/quantities/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value(100.0))
                .andExpect(jsonPath("$.error").value(false));
    }

    @Test
    void testVolumeConversion() throws Exception {

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO("CONVERT", "VOLUME", 1.0, 1000.0, false, null, null);

        when(service.convert(any())).thenReturn(response);

        QuantityDTO dto = new QuantityDTO();
        dto.setValue(1.0);
        dto.setFromUnit("LITER");
        dto.setToUnit("MILLILITER");
        dto.setMeasurementType("VOLUME");

        mockMvc.perform(post("/api/v1/quantities/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value(1000.0))
                .andExpect(jsonPath("$.error").value(false));
    }

    @Test
    void testTemperatureConversion() throws Exception {

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO("CONVERT", "TEMPERATURE", 0.0, 32.0, false, null, null);

        when(service.convert(any())).thenReturn(response);

        QuantityDTO dto = new QuantityDTO();
        dto.setValue(0.0);
        dto.setFromUnit("CELSIUS");
        dto.setToUnit("FAHRENHEIT");
        dto.setMeasurementType("TEMPERATURE");

        mockMvc.perform(post("/api/v1/quantities/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value(32.0))
                .andExpect(jsonPath("$.error").value(false));
    }
}