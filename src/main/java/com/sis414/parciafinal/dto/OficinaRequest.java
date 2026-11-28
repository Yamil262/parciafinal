package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: oficina.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record OficinaRequest(
        @Size(max = 4) String entidad,
        @Size(max = 5) String unidad,
        @PositiveOrZero @Max(9999) Integer codOfic,
        @NotBlank @Size(max = 65) String nomOfic,
        @Size(max = 4000) String observ,
        @Size(max = 8) String usuar
) {
}
