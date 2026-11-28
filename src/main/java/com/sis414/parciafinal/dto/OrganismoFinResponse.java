package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de organismo financiador (sin datos sensibles). */
public record OrganismoFinResponse(
        Integer gestion,
        String codOf,
        String des,
        String sigla
) {
}
