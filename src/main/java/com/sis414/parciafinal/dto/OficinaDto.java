package com.sis414.parciafinal.dto;

import java.time.LocalDate;
public record OficinaDto(
    Long id,
    Integer codOfic,
    String nomOfic,
    String observ,
    LocalDate feult,
    String usuar,
    Integer apiEstado,
    Long unidadId
) {}
