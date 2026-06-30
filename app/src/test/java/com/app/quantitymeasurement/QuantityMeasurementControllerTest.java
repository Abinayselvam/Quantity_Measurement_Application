package com.app.quantitymeasurement;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
@AutoConfigureMockMvc(addFilters = false)
class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IQuantityMeasurementService service;

    private QuantityInputDTO buildLengthInput() {
        QuantityDTO first = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO second = new QuantityDTO(12.0, "INCHES", "LengthUnit");
        return new QuantityInputDTO(first, second, null);
    }

    @Test
    void testRestEndpointCompareQuantities() throws Exception {
        QuantityMeasurementDTO response = new QuantityMeasurementDTO();
        response.setOperation("compare");
        response.setResultString("true");
        response.setError(false);

        when(service.compare(any(QuantityInputDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/quantities/compare")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildLengthInput())))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.resultString").value("true"))
                .andExpect(jsonPath("$.error").value(false));

        verify(service).compare(any(QuantityInputDTO.class));
    }

    @Test
    void testRestEndpointAddQuantities() throws Exception {
        QuantityMeasurementDTO response = new QuantityMeasurementDTO();
        response.setOperation("add");
        response.setResultValue(2.0);
        response.setResultUnit("FEET");
        response.setResultMeasurementType("LengthUnit");

        when(service.add(any(QuantityInputDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildLengthInput())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value(2.0))
                .andExpect(jsonPath("$.resultUnit").value("FEET"));
    }

    @Test
    void testRestEndpointConvertQuantities() throws Exception {
        QuantityMeasurementDTO response = new QuantityMeasurementDTO();
        response.setOperation("convert");
        response.setResultValue(12.0);

        when(service.convert(any(QuantityInputDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/quantities/convert")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildLengthInput())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value(12.0));
    }

    @Test
    void testRestEndpointInvalidInput_Returns400() throws Exception {
        QuantityDTO invalid = new QuantityDTO(1.0, "FOOT", "LengthUnit");
        QuantityDTO valid = new QuantityDTO(12.0, "INCHES", "LengthUnit");
        QuantityInputDTO input = new QuantityInputDTO(invalid, valid, null);

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetOperationHistory() throws Exception {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setOperation("compare");

        when(service.getOperationHistory("COMPARE")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/quantities/history/operation/COMPARE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].operation").value("compare"));
    }

    @Test
    void testGetOperationCount() throws Exception {
        when(service.getOperationCount("COMPARE")).thenReturn(3L);

        mockMvc.perform(get("/api/v1/quantities/count/COMPARE"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    void testGetErrorHistory() throws Exception {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setOperation("add");
        dto.setError(true);
        dto.setErrorMessage("Cannot perform arithmetic between different measurement categories");

        when(service.getErrorHistory()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/quantities/history/errored"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].error").value(true));
    }
}
