package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: entidad.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record EntidadesRequest(
        @PositiveOrZero @Max(9999) Integer gestion,
        @Size(max = 4) String entidad,
        @NotBlank @Size(max = 110) String descEnt,
        @Size(max = 15) String siglaEnt,
        @PositiveOrZero @Max(9) Integer sectorEnt,
        @PositiveOrZero @Max(9) Integer subsecEnt,
        @PositiveOrZero @Max(9) Integer areaEnt,
        @PositiveOrZero @Max(9) Integer subAreaEnt,
        @PositiveOrZero @Max(9) Integer nivelInst
) {
}
