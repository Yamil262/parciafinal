package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: estado del bien.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record EstadoRequest(
        @PositiveOrZero @Max(99) Integer codEstado,
        @NotBlank @Size(max = 10) String nomEstado
) {
}
