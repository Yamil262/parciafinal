package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: transferencia.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record TrasfeRequest(
        @NotBlank @Size(max = 4) String entidad,
        @NotBlank @Size(max = 5) String unidad,
        @NotBlank @Size(max = 15) String codigo,
        @NotNull @PositiveOrZero @Max(9999) Integer codOfic,
        @NotNull @PositiveOrZero @Max(99999) Integer codResp,
        @Size(max = 8) String usuar,
        @Size(max = 5) String unidadIni
) {
}
