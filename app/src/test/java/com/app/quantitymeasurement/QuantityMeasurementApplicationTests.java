package com.app.quantitymeasurement;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuantityMeasurementApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private QuantityMeasurementRepository repository;

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    private HttpEntity<QuantityInputDTO> jsonEntity(QuantityInputDTO body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    @Test
    void testSpringBootApplicationStarts() {
        assertTrue(port > 0);
    }

    @Test
    void testRestEndpointCompareQuantities() {
        QuantityDTO first = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO second = new QuantityDTO(12.0, "INCHES", "LengthUnit");
        QuantityInputDTO input = new QuantityInputDTO(first, second, null);

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.postForEntity(
                url("/api/v1/quantities/compare"), jsonEntity(input), QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("true", response.getBody().getResultString());
    }

    @Test
    void testRestEndpointAddQuantities() {
        QuantityDTO first = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO second = new QuantityDTO(12.0, "INCHES", "LengthUnit");
        QuantityInputDTO input = new QuantityInputDTO(first, second, null);

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.postForEntity(
                url("/api/v1/quantities/add"), jsonEntity(input), QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2.0, response.getBody().getResultValue());
        assertEquals("FEET", response.getBody().getResultUnit());
    }

    @Test
    void testRestEndpointConvertQuantities() {
        QuantityDTO first = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO target = new QuantityDTO(0.0, "INCHES", "LengthUnit");
        QuantityInputDTO input = new QuantityInputDTO(first, target, null);

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.postForEntity(
                url("/api/v1/quantities/convert"), jsonEntity(input), QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(12.0, response.getBody().getResultValue());
    }

    @Test
    void testRestEndpointInvalidInput_Returns400() {
        QuantityDTO invalid = new QuantityDTO(1.0, "FOOT", "LengthUnit");
        QuantityDTO valid = new QuantityDTO(12.0, "INCHE", "LengthUnit");
        QuantityInputDTO input = new QuantityInputDTO(invalid, valid, null);

        ResponseEntity<String> response = restTemplate.postForEntity(
                url("/api/v1/quantities/add"), jsonEntity(input), String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testRestEndpointIncompatibleCategories_Returns400() {
        QuantityDTO first = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO second = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        QuantityInputDTO input = new QuantityInputDTO(first, second, null);

        ResponseEntity<String> response = restTemplate.postForEntity(
                url("/api/v1/quantities/add"), jsonEntity(input), String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testRestEndpointDivideByZero_Returns500() {
        QuantityDTO first = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO second = new QuantityDTO(0.0, "INCHES", "LengthUnit");
        QuantityInputDTO input = new QuantityInputDTO(first, second, null);

        ResponseEntity<String> response = restTemplate.postForEntity(
                url("/api/v1/quantities/divide"), jsonEntity(input), String.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testSwaggerUILoads() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                url("/swagger-ui/index.html"), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testOpenAPIDocumentation() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                url("/api-docs"), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("openapi"));
    }

    @Test
    void testH2ConsoleLaunches() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                url("/h2-console"), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testActuatorHealthEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                url("/actuator/health"), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("UP"));
    }

    @Test
    void testGetOperationCountEndpoint() {
        QuantityDTO first = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO second = new QuantityDTO(12.0, "INCHES", "LengthUnit");
        QuantityInputDTO input = new QuantityInputDTO(first, second, null);

        restTemplate.postForEntity(url("/api/v1/quantities/compare"), jsonEntity(input), QuantityMeasurementDTO.class);

        ResponseEntity<Long> response = restTemplate.exchange(
                url("/api/v1/quantities/count/compare"), HttpMethod.GET, null, Long.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() >= 1);
    }
}
