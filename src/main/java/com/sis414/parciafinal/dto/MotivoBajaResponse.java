package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de motivo de baja (sin datos sensibles). */
public record MotivoBajaResponse(
        Integer codBaja,
        String descBaja
) {
}
