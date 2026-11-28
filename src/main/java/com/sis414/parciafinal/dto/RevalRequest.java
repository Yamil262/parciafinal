package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: revaluación.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record RevalRequest(
        @NotBlank @Size(max = 4) String entidad,
        @NotBlank @Size(max = 5) String unidad,
        @NotBlank @Size(max = 15) String codigo,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal costo,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal depAcuAnt,
        @PositiveOrZero @Max(99) Integer dReval,
        @PositiveOrZero @Max(99) Integer dia,
        @PositiveOrZero @Max(99) Integer mReval,
        @PositiveOrZero @Max(99) Integer mes,
        @PositiveOrZero @Max(9999) Integer aReval,
        @PositiveOrZero @Max(9999) Integer ano,
        @PositiveOrZero @Digits(integer = 2, fraction = 2) BigDecimal vidaUtil,
        @Size(max = 16) String resolucion,
        @Size(max = 4000) String observ,
        @Size(max = 8) String usuar,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal depAcu,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal actua,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal depGestion
) {
}
