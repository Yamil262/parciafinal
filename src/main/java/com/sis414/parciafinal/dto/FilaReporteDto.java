package com.sis414.parciafinal.dto;

import java.math.BigDecimal;

public record FilaReporteDto(
        String entidad, String unidad, String codigo, String descripcion, String grupo, String auxiliar,
        String oficina, String responsable, BigDecimal costo, BigDecimal factor, BigDecimal valorActual,
        BigDecimal depGestion, BigDecimal depAcumulada, BigDecimal valorNeto
) {}
