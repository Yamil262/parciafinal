package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: unidad administrativa.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record UnidadAdminRequest(
        @Size(max = 4) String entidad,
        @Size(max = 5) String unidad,
        @NotBlank @Size(max = 120) String descrip,
        @Size(max = 20) String ciudad,
        @PositiveOrZero @Max(9) Integer estadoUni
) {
}
