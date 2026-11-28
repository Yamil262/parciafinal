package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de revaluación (sin datos sensibles). */
public record RevalResponse(
        Long id,
        String entidad,
        String unidad,
        String codigo,
        BigDecimal costo,
        BigDecimal depAcuAnt,
        Integer dReval,
        Integer dia,
        Integer mReval,
        Integer mes,
        Integer aReval,
        Integer ano,
        BigDecimal vidaUtil,
        String resolucion,
        String observ,
        String usuar,
        LocalDate feult,
        BigDecimal depAcu,
        BigDecimal actua,
        BigDecimal depGestion,
        LocalDate fecMod,
        String usuMod,
        String activoDescrip
) {
}
