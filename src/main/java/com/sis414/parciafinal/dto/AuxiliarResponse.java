package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de auxiliar contable (sin datos sensibles). */
public record AuxiliarResponse(
        String entidad,
        String unidad,
        Integer codCont,
        Integer codAux,
        String nomAux,
        String observ,
        LocalDate feult,
        String usuar,
        String unidadDescrip,
        String cuentaNombre
) {
}
