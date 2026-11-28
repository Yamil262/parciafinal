package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de estructura institucional (sin datos sensibles). */
public record EstEntResponse(
        Integer gestion,
        Integer sectorEnt,
        Integer subsecEnt,
        Integer areaEnt,
        Integer subAreaEnt,
        Integer nivelInst,
        String desEstruc,
        String siglaEstru,
        String apropiable
) {
}
