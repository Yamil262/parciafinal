package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: mes.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record MesRequest(
        @Min(1) @Max(12) Integer mes,
        @NotBlank @Size(max = 10) String nomMes
) {
}
