package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: auxiliar contable.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record AuxiliarRequest(
        @Size(max = 4) String entidad,
        @Size(max = 5) String unidad,
        @PositiveOrZero @Max(99) Integer codCont,
        @PositiveOrZero @Max(999) Integer codAux,
        @NotBlank @Size(max = 50) String nomAux,
        @Size(max = 4000) String observ,
        @Size(max = 8) String usuar
) {
}
