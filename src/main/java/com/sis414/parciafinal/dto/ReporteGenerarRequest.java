package com.sis414.parciafinal.dto;

import java.math.BigDecimal;

/**
 * Datos para generar reportes personalizados.
 * Los filtros null representan la opcion TODOS.
 */
public record ReporteGenerarRequest(
        Long entidadId,
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
