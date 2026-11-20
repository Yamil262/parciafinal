package com.sis414.parciafinal.dto;

public record ReporteDto(
    Long id,
    String reportName,
    String contents,
    String modType,
    String notes
) {}
