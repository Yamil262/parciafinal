package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: cuenta contable.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record CodContRequest(
        @PositiveOrZero @Max(99) Integer codCont,
        @NotBlank @Size(max = 50) String nombre,
        @NotNull @PositiveOrZero @Max(9999) Integer vidaUtil,
        @Size(max = 4000) String observ,
         Boolean depreciar,
         Boolean actualizar,
        @Size(max = 8) String usuar
) {
}
