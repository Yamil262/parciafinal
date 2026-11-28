package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de transferencia (sin datos sensibles). */
public record TrasfeResponse(
        Long id,
        String entidad,
        String unidad,
        String codigo,
        Integer codOfic,
        Integer codResp,
        LocalDate feult,
        String usuar,
        String unidadIni,
        String activoDescrip,
        String oficinaNombre,
        String responsableNombre
) {
}
