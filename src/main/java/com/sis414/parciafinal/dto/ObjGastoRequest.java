package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: partida de gasto.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record ObjGastoRequest(
        @PositiveOrZero @Max(9999) Integer gestion,
        @Size(max = 5) String partida,
        @NotBlank @Size(max = 50) String descrip
) {
}
