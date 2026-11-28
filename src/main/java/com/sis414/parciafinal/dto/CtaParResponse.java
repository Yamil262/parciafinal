package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de relación cuenta-partida (sin datos sensibles). */
public record CtaParResponse(
        Integer codCont,
        String partida,
        Integer gestion,
        String cuentaNombre,
        String partidaDescrip
) {
}
