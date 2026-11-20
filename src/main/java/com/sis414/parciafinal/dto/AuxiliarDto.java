package com.sis414.parciafinal.dto;

import java.time.LocalDate;
public record AuxiliarDto(
    Long id,
    Integer codAux,
    String nomAux,
    String observ,
    LocalDate feult,
    String usuar,
    Long unidadId,
    Long grupoId
) {}
