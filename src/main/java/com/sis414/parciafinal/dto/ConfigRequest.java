package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: configuración.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record ConfigRequest(
        @NotBlank @Size(max = 4) String entidad,
        @Size(max = 120) String descEnt,
        @Size(max = 5) String unidad,
        @Size(max = 120) String descUni,
        @NotBlank @Size(max = 10) String usuario
) {
}
