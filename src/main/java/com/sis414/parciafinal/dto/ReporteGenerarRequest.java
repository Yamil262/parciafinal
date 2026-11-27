package com.sis414.parciafinal.dto;

import java.math.BigDecimal;

/** Pantalla "REPORTES A MEDIDA". Un filtro en null equivale al botón "Todos"; sin entidad/unidad equivale a "Consolidado". */
public record ReporteGenerarRequest(
        String entidad,
        String unidad,
        Integer codCont,
        Integer codAux,
        String codigo,
        Integer codOfic,
        Integer codResp,
        Integer anoInicial,
        Integer anoFinal,
        Integer dia,
        Integer mes,
        Integer ano,
        BigDecimal ufv
) {}
