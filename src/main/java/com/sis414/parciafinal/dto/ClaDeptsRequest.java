package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: departamento.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record ClaDeptsRequest(
        @PositiveOrZero @Max(99) Integer codigo,
        @NotBlank @Size(max = 15) String descripcion,
        @NotBlank @Size(max = 10) String sigla
) {
}
