package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de entidad (sin datos sensibles). */
public record EntidadesResponse(
        Integer gestion,
        String entidad,
        String descEnt,
        String siglaEnt,
        Integer sectorEnt,
        Integer subsecEnt,
        Integer areaEnt,
        Integer subAreaEnt,
        Integer nivelInst,
        String estructuraDescripcion
) {
}
