package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de unidad administrativa (sin datos sensibles). */
public record UnidadAdminResponse(
        String entidad,
        String unidad,
        String descrip,
        String ciudad,
        Integer estadoUni
) {
}
