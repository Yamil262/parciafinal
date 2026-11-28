package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: organismo financiador.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record OrganismoFinRequest(
        @PositiveOrZero @Max(9999) Integer gestion,
        @Size(max = 3) String codOf,
        @NotBlank @Size(max = 100) String des,
        @Size(max = 15) String sigla
) {
}
