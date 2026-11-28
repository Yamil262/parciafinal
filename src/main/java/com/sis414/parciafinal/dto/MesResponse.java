package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de mes (sin datos sensibles). */
public record MesResponse(
        Integer mes,
        String nomMes
) {
}
