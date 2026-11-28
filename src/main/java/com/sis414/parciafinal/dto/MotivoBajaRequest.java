package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: motivo de baja.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record MotivoBajaRequest(
        @PositiveOrZero @Max(99) Integer codBaja,
        @NotBlank @Size(max = 120) String descBaja
) {
}
