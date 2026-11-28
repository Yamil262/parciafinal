package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: estructura institucional.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record EstEntRequest(
        @PositiveOrZero @Max(9999) Integer gestion,
        @PositiveOrZero @Max(9) Integer sectorEnt,
        @PositiveOrZero @Max(9) Integer subsecEnt,
        @PositiveOrZero @Max(9) Integer areaEnt,
        @PositiveOrZero @Max(9) Integer subAreaEnt,
        @PositiveOrZero @Max(9) Integer nivelInst,
        @NotBlank @Size(max = 60) String desEstruc,
        @Size(max = 15) String siglaEstru,
        @Size(max = 1) String apropiable
) {
}
