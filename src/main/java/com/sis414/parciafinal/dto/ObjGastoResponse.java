package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de partida de gasto (sin datos sensibles). */
public record ObjGastoResponse(
        Integer gestion,
        String partida,
        String descrip
) {
}
