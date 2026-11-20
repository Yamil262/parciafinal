package com.sis414.parciafinal.dto;

import java.math.BigDecimal;

/** Bloque inferior de la pantalla Activos Fijos: VIDA, % DEPRECIA, DEP.GESTION, VALOR NETO, CALCULADOS A. */
public record CalculoActivoDto(
        BigDecimal costoInicial,
        BigDecimal depAcuInicial,
        BigDecimal factorActual,
        BigDecimal valorActual,
        BigDecimal vidaAnios,
        Integer diasTranscurridos,
        BigDecimal porcentajeDeprecia,
        BigDecimal depGestion,
        BigDecimal depAcumulada,
        BigDecimal valorNeto,
        FechaCalculoDto calculadoA,
        BigDecimal ufv
) {}
