package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: relación cuenta-partida.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record CtaParRequest(
        @PositiveOrZero @Max(99) Integer codCont,
        @Size(max = 5) String partida,
        @PositiveOrZero @Max(9999) Integer gestion
) {
}
