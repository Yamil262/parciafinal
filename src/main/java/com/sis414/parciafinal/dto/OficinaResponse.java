package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de oficina (sin datos sensibles). */
public record OficinaResponse(
        String entidad,
        String unidad,
        Integer codOfic,
        String nomOfic,
        String observ,
        LocalDate feult,
        String usuar,
        Integer apiEstado,
        String unidadDescrip
) {
}
