package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de cuenta contable (sin datos sensibles). */
public record CodContResponse(
        Integer codCont,
        String nombre,
        Integer vidaUtil,
        String observ,
        Boolean depreciar,
        Boolean actualizar,
        LocalDate feult,
        String usuar
) {
}
