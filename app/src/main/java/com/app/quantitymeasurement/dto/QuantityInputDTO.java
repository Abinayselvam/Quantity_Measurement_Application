package com.app.quantitymeasurement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Encapsulates the input data for a quantity measurement REST operation.
 * Used as the @RequestBody for compare, convert, add, subtract, and divide endpoints.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityInputDTO {

    @NotNull(message = "thisQuantityDTO cannot be null")
    @Valid
    private QuantityDTO thisQuantityDTO;

    @NotNull(message = "thatQuantityDTO cannot be null")
    @Valid
    private QuantityDTO thatQuantityDTO;

    /**
     * Optional target unit name for conversion/arithmetic operations that
     * require an explicit result unit. If omitted, the unit of
     * thisQuantityDTO is used as the target.
     */
    private String targetUnit;
}
