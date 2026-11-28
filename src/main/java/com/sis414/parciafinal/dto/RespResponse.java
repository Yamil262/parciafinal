package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de responsable (sin datos sensibles). El CI se muestra enmascarado. */
public record RespResponse(
        String entidad,
        String unidad,
        Integer codOfic,
        Integer codResp,
        String nomResp,
        String cargo,
        String observ,
        String ciEnmascarado,
        LocalDate feult,
        String usuar,
        Integer codExp,
        Integer apiEstado,
        String oficinaNombre
) {
}
