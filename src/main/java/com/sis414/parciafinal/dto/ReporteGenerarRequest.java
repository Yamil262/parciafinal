package com.sis414.parciafinal.dto;

import java.math.BigDecimal;

/** Pantalla "REPORTES A MEDIDA". Un filtro en null equivale al boton "Todos"; unidadId null equivale a "Consolidado". */
public record ReporteGenerarRequest(
        Long unidadId,
        Long grupoId,
        Long auxiliarId,
        Long activoId,
        Long oficinaId,
        Long responsableId,
        Integer anoInicial,
        Integer anoFinal,
        Integer dia,
        Integer mes,
        Integer ano,
        BigDecimal ufv
) {}
