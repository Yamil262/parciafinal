package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: responsable.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record RespRequest(
        @Size(max = 4) String entidad,
        @Size(max = 5) String unidad,
        @PositiveOrZero @Max(9999) Integer codOfic,
        @PositiveOrZero @Max(99999) Integer codResp,
        @NotBlank @Size(max = 35) String nomResp,
        @Size(max = 35) String cargo,
        @Size(max = 4000) String observ,
        @Size(max = 20) String ci,
        @Size(max = 8) String usuar,
        @PositiveOrZero @Max(99) Integer codExp
) {
}
