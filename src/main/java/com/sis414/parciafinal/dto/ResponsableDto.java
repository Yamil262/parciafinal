package com.sis414.parciafinal.dto;

import java.time.LocalDate;
public record ResponsableDto(
    Long id,
    Integer codResp,
    String nomResp,
    String cargo,
    String observ,
    String ci,
    LocalDate feult,
    String usuar,
    Integer apiEstado,
    Long oficinaId,
    Long departamentoExpId
) {}
