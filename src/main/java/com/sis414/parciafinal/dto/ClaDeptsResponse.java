package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de departamento (sin datos sensibles). */
public record ClaDeptsResponse(
        Integer codigo,
        String descripcion,
        String sigla
) {
}
